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

        /* --- NUEVOS ESTILOS PARA EL FORMULARIO --- */
        #newOrderForm {
            border: 1px solid #eee;
            padding: 20px;
            border-radius: 8px;
            background: #f9f9f9;
            max-width: 600px;
        }
        #newOrderForm div {
            display: grid;
            grid-template-columns: 180px 1fr; /* Columna de label | Columna de input */
            align-items: center;
            gap: 10px;
            margin-bottom: 12px;
        }
         #newOrderForm label {
            font-weight: bold;
            text-align: right;
         }
        #newOrderForm textarea, #newOrderForm select {
            width: 100%;
            box-sizing: border-box; /* Para que el padding no rompa el ancho */
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        #newOrderForm button {
            grid-column: 2; /* Alinear el botón con los inputs */
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        #newOrderForm button:hover { background-color: #0056b3; }
        /* --- FIN DE NUEVOS ESTILOS --- */
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
                 <option value="">-- Cargando dispositivos... --</option>
            </select>
        </div>
        <div>
            <label for="issueDescription">Descripción del Problema (min 10):</label>
            <textarea id="issueDescription" minlength="10" required rows="3"></textarea>
        </div>
        <div>
            <label></label> <button type="submit">Enviar Solicitud</button>
        </div>
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
        // Esta es la configuración correcta:
        // 1. SIEMPRE usar <%@ page isELIgnored="true" %>
        // 2. NUNCA usar la barra '\' en el JavaScript.

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
                // 🚨 CORREGIDO: Sin '\'
                const message = errorData.error || `Error ${response.status}`;
                throw new Error(message);
            }
            return response.status === 204 ? null : response.json();
        }

        async function loadDevices() {
             try {
                const devices = await fetch('/api/devices').then(handleResponse);
                const select = document.getElementById('deviceSelect');
                select.innerHTML = '<option value="">-- Seleccione un dispositivo --</option>'; // Limpiar

                devices.forEach(device => {
                    const option = document.createElement('option');
                    option.value = device.id;
                    // 🚨 CORREGIDO: Sin '\'
                    option.textContent = `${device.brand} ${device.model} (ID: ${device.id})`;
                    select.appendChild(option);
                });
             } catch (e) {
                alert('Error cargando dispositivos: ' + e.message);
             }
        }

        async function loadMyOrders() {
            try {
                const allOrders = await fetch('/api/orders').then(handleResponse);
                const myOrders = allOrders.filter(order => order.customer && order.customer.id == CUSTOMER_ID);
                const tbody = document.getElementById('ordersTableBody');
                tbody.innerHTML = '';

                myOrders.forEach(order => {
                    const tr = document.createElement('tr');

                    // 🚨 CORREGIDO: Sin '\'
                    tr.innerHTML = `
                        <td>${order.id}</td>
                        <td>${order.device.brand} ${order.device.model}</td>
                        <td>${order.status}</td>
                        <td>${order.issueDescription}</td>
                        <td>${order.techNotes || 'N/A'}</td>
                        <td>
                            ${order.status === 'PENDING'
                                ? `<button onclick="cancelOrder(${order.id})">Cancelar</button>`
                                : 'No disponible'}
                        </td>
                    `;

                    tbody.appendChild(tr);
                });
            } catch (error) {
                alert(`Error cargando órdenes: ${error.message}`);
            }
        }

        async function createOrder(event) {
            event.preventDefault();
            const deviceId = document.getElementById('deviceSelect').value;
            const issueDescription = document.getElementById('issueDescription').value;

            if (!deviceId) {
                alert("Por favor, seleccione un dispositivo.");
                return;
            }

            try {
                // 🚨 CORREGIDO: Sin '\'
                await fetch(`/api/orders/${CUSTOMER_ID}`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ deviceId: parseInt(deviceId), issueDescription })
                }).then(handleResponse);

                loadMyOrders();
                document.getElementById('newOrderForm').reset();
            } catch (error) {
                alert(`Error al crear la orden: ${error.message}`);
            }
        }

        async function cancelOrder(orderId) {
             if (confirm('¿Seguro que quieres cancelar esta orden?')) {
                try {
                    // 🚨 CORREGIDO: Sin '\'
                    await fetch(`/api/orders/${orderId}/cancel/${CUSTOMER_ID}`, { method: 'DELETE' }).then(handleResponse);
                    loadMyOrders();
                } catch (error) {
                    alert(`Error al cancelar: ${error.message}`);
                }
             }
        }

        document.addEventListener('DOMContentLoaded', () => {
            checkSession();
            loadDevices(); // Llamar a la función para cargar dispositivos
            loadMyOrders();
            document.getElementById('newOrderForm').addEventListener('submit', createOrder);
        });
    </script>
</body>
</html>