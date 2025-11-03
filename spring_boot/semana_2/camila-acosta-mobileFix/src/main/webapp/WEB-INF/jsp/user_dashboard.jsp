<%@ page isELIgnored="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Dashboard</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        form { margin-bottom: 20px; }
        .logout-form { display: inline; }
    </style>
</head>
<body>
    <h1>Mis Órdenes (USER)</h1>
    <form class="logout-form" onsubmit="logout(event)">
        <button type="submit">Cerrar Sesión</button>
    </form>

    <hr>

    <h3>Crear Nueva Orden</h3>
    <form id="newOrderForm">
        <div>
            <label for="deviceSelect">Dispositivo:</label>
            <select id="deviceSelect" required>
                </select>
        </div>
        <div>
            <label for="issueDescription">Descripción del Problema (min 10):</label>
            <textarea id="issueDescription" minlength="10" required style="width: 300px; height: 60px;"></textarea>
        </div>
        <button type="submit">Enviar Solicitud</button>
    </form>

    <hr>

    <h3>Mis Órdenes</h3>
    <table id="ordersTable">
        <thead>
            <tr>
                <th>ID</th>
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

    <script>
        const CUSTOMER_ID = localStorage.getItem('userId');

        function checkSession() {
            if (!CUSTOMER_ID || localStorage.getItem('userRole') !== 'USER') {
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

        async function loadDevices() {
             // ...
        }

        async function loadMyOrders() {
            try {
                const allOrders = await fetch('/api/orders').then(handleResponse);
                const myOrders = allOrders.filter(order => order.customer && order.customer.id == CUSTOMER_ID);
                const tbody = document.getElementById('ordersTableBody');
                tbody.innerHTML = '';

                myOrders.forEach(order => {
                    const tr = document.createElement('tr');

                    // 🚨 INICIO DE LA CORRECCIÓN: Se usan comillas simples ' ' para el botón
                    tr.innerHTML = `
                        <td>\${order.id}</td>
                        <td>\${order.device.brand} \${order.device.model}</td>
                        <td>\${order.status}</td>
                        <td>\${order.issueDescription}</td>
                        <td>\${order.techNotes || 'N/A'}</td>
                        <td>
                            \${order.status === 'PENDING' ?
                              '<button onclick="cancelOrder(\${order.id})">Cancelar</button>' :
                              'No disponible'}
                        </td>
                    `;
                    // 🚨 FIN DE LA CORRECCIÓN

                    tbody.appendChild(tr);
                });
            } catch (error) {
                alert(`Error cargando órdenes: \${error.message}`);
            }
        }

        async function createOrder(event) {
            event.preventDefault();
            const deviceId = document.getElementById('deviceSelect').value;
            const issueDescription = document.getElementById('issueDescription').value;

            try {
                await fetch(`/api/orders/\${CUSTOMER_ID}`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ deviceId: parseInt(deviceId), issueDescription })
                }).then(handleResponse);

                loadMyOrders();
                document.getElementById('newOrderForm').reset();
            } catch (error) {
                alert(`Error al crear la orden: \${error.message}`);
            }
        }

        async function cancelOrder(orderId) {
             if (confirm('¿Seguro que quieres cancelar esta orden?')) {
                try {
                    await fetch(`/api/orders/\${orderId}/cancel/\${CUSTOMER_ID}`, { method: 'DELETE' }).then(handleResponse);
                    loadMyOrders();
                } catch (error) {
                    alert(`Error al cancelar: \${error.message}`);
                }
             }
        }

        document.addEventListener('DOMContentLoaded', () => {
            checkSession();
            // loadDevices();
            loadMyOrders();
            document.getElementById('newOrderForm').addEventListener('submit', createOrder);
        });
    </script>
</body>
</html>