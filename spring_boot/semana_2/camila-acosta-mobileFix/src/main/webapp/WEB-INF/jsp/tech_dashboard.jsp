<%@ page isELIgnored="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tech Dashboard</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .logout-form { display: inline; }
        .modal { display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgba(0,0,0,0.4); }
        .modal-content { background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 80%; max-width: 500px; }
        .close { color: #aaa; float: right; font-size: 28px; font-weight: bold; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Órdenes Asignadas (TECH)</h1>
    <form class="logout-form" onsubmit="logout(event)">
        <button type="submit">Cerrar Sesión</button>
    </form>

    <hr>

    <table id="ordersTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Dispositivo</th>
                <th>Estado</th>
                <th>Problema</th>
                <th>Notas Técnicas</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody id="ordersTableBody">
            </tbody>
    </table>

    <div id="statusModal" class="modal">
      <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h3>Actualizar Orden <span id="modalOrderId"></span></h3>
        <form id="statusForm">
            <input type="hidden" id="modalOrderIdInput">
            <div>
                <label for="statusSelect">Nuevo Estado:</label>
                <select id="statusSelect" required>
                    </select>
            </div>
            <div>
                <label for="techNotes">Nuevas Notas Técnicas:</label>
                <textarea id="techNotes" style="width: 100%; height: 80px;"></textarea>
            </div>
            <button type="submit">Actualizar</button>
        </form>
      </div>
    </div>

    <script>
        const TECH_ID = localStorage.getItem('userId');

        function checkSession() {
            if (!TECH_ID || localStorage.getItem('userRole') !== 'TECH') {
                alert('Acceso no autorizado. Redirigiendo a Login.');
                window.location.href = '/login';
            }
        }

        function logout(event) {
            event.preventDefault();
            localStorage.removeItem('userId');
            localStorage.removeItem('userRole');
            window.location.href = '/login';
        }

        async function handleResponse(response) {
            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                const message = errorData.error || `Error \${response.status}`;
                throw new Error(message);
            }
            return response.status === 204 ? null : response.json();
        }

        async function loadAssignedOrders() {
            try {
                const allOrders = await fetch('/api/orders').then(handleResponse);
                const assignedOrders = allOrders.filter(order => order.assignedTech && order.assignedTech.id == TECH_ID);
                const tbody = document.getElementById('ordersTableBody');
                tbody.innerHTML = '';

                assignedOrders.forEach(order => {
                    const tr = document.createElement('tr');

                    // 🚨 INICIO DE LA CORRECCIÓN: Se usan comillas simples ' ' para el botón
                    tr.innerHTML = `
                        <td>\${order.id}</td>
                        <td>\${order.customer.fullName || order.customer.username}</td>
                        <td>\${order.device.brand} \${order.device.model}</td>
                        <td>\${order.status}</td>
                        <td>\${order.issueDescription}</td>
                        <td>\${order.techNotes || 'N/A'}</td>
                        <td>
                            \${order.status !== 'DELIVERED' && order.status !== 'CANCELED' ?
                              '<button onclick="openModal(\${order.id}, \'\${order.status}\')">Actualizar</button>' :
                              'Finalizada'}
                        </td>
                    `;
                    // 🚨 FIN DE LA CORRECCIÓN

                    tbody.appendChild(tr);
                });
            } catch (error) {
                alert(`Error cargando órdenes: \${error.message}`);
            }
        }

        async function updateStatus(event) {
            event.preventDefault();
            const orderId = document.getElementById('modalOrderIdInput').value;
            const status = document.getElementById('statusSelect').value;
            const techNotes = document.getElementById('techNotes').value;

            if (!status) {
                alert("No hay un estado válido seleccionado.");
                return;
            }

            try {
                await fetch(`/api/orders/\${orderId}/status/\${TECH_ID}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ status, techNotes })
                }).then(handleResponse);

                closeModal();
                loadAssignedOrders();
            } catch (error) {
                alert(`Error al actualizar: \${error.message}`);
            }
        }

        document.addEventListener('DOMContentLoaded', () => {
            checkSession();
            loadAssignedOrders();
            document.getElementById('statusForm').addEventListener('submit', updateStatus);
        });

        function openModal(orderId, currentStatus) {
            document.getElementById('modalOrderId').textContent = `(ID: \${orderId})`;
            document.getElementById('modalOrderIdInput').value = orderId;
            document.getElementById('techNotes').value = '';

            const statusSelect = document.getElementById('statusSelect');
            statusSelect.innerHTML = '';

            const allowedTransitions = {
                'PENDING': ['IN_PROGRESS'],
                'IN_PROGRESS': ['READY'],
                'READY': ['DELIVERED'],
                'DELIVERED': [],
                'CANCELED': []
            };

            const transitions = allowedTransitions[currentStatus] || [];
            if (transitions.length === 0) {
                 statusSelect.innerHTML = '<option value="">No hay transiciones válidas</option>';
            } else {
                transitions.forEach(status => {
                    const option = document.createElement('option');
                    option.value = status;
                    option.textContent = status;
                    statusSelect.appendChild(option);
                });
            }

            document.getElementById('statusModal').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('statusModal').style.display = 'none';
        }

        window.onclick = function(event) {
            const modal = document.getElementById('statusModal');
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</body>
</html>