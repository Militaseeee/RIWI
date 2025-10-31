<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .logout-form { display: inline; }

        /* Estilos para Pestañas */
        .tab { overflow: hidden; border-bottom: 1px solid #ccc; }
        .tab button { background-color: inherit; float: left; border: none; outline: none; cursor: pointer; padding: 14px 16px; transition: 0.3s; }
        .tab button:hover { background-color: #ddd; }
        .tab button.active { background-color: #ccc; }
        .tabcontent { display: none; padding: 6px 12px; border-top: none; }
    </style>
</head>
<body>
    <h1>Panel de Administración (ADMIN)</h1>
    <form class="logout-form" onsubmit="logout(event)">
        <button type="submit">Cerrar Sesión</button>
    </form>
    <hr>

    <div class="tab">
        <button class="tablinks active" onclick="openTab(event, 'Orders')">Órdenes</button>
        <button class="tablinks" onclick="openTab(event, 'Devices')">Dispositivos</button>
        <button class="tablinks" onclick="openTab(event, 'Users')">Usuarios</button>
    </div>

    <div id="Orders" class="tabcontent" style="display: block;">
        <h3>Todas las Órdenes</h3>
        <table id="adminOrdersTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Dispositivo</th>
                    <th>Estado</th>
                    <th>Técnico Asignado</th>
                    <th>Acción (Asignar)</th>
                    <th>Acción (Cancelar)</th>
                </tr>
            </thead>
            <tbody id="adminOrdersBody"></tbody>
        </table>
        <select id="techSelect" style="margin-top: 10px;">
            <option value="">-- Seleccionar Técnico --</option>
        </select>
    </div>

    <div id="Devices" class="tabcontent">
        <h3>Gestionar Dispositivos</h3>
        <form id="deviceForm">
            <input type="hidden" id="deviceId">
            <input type="text" id="deviceBrand" placeholder="Marca" required>
            <input type="text" id="deviceModel" placeholder="Modelo" required>
            <input type="text" id="deviceSerial" placeholder="Serial (opcional)">
            <button type="submit">Guardar Dispositivo</button>
            <button type="button" onclick="resetDeviceForm()">Nuevo</button>
        </form>
        <table id="devicesTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Serial</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody id="devicesBody"></tbody>
        </table>
    </div>

    <div id="Users" class="tabcontent">
        <h3>Gestionar Usuarios</h3>
         <form id="userForm">
            <input type="text" id="userUsername" placeholder="Username" required>
            <input type="password" id="userPassword" placeholder="Password (min 6)" required>
            <input type="email" id="userEmail" placeholder="Email">
            <input type="text" id="userFullName" placeholder="Nombre Completo">
            <select id="userRole" required>
                <option value="USER">USER</option>
                <option value="TECH">TECH</option>
                <option value="ADMIN">ADMIN</option>
            </select>
            <button type="submit">Crear Usuario</button>
        </form>
        <table id="usersTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Rol</th>
                </tr>
            </thead>
            <tbody id="usersBody"></tbody>
        </table>
    </div>

    <script>
        // 🚨 CAMBIO CLAVE: Obtener el ID del Admin para usarlo en acciones de cancelación (si las hubiera)
        const ACTOR_ID = localStorage.getItem('userId');

        function checkSession() {
            if (!ACTOR_ID || localStorage.getItem('userRole') !== 'ADMIN') {
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

        // --- Lógica de Pestañas ---
        function openTab(evt, tabName) {
            let i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }

        // --- Helper de Fetch ---
        async function handleResponse(response) {
            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                const message = errorData.error || `Error ${response.status}`;
                throw new Error(message);
            }
            return response.status === 204 ? null : response.json();
        }

        // --- Lógica de Órdenes ---
        async function loadAllOrders() {
            try {
                // El servicio ahora devuelve TODAS las órdenes sin filtro
                const orders = await fetch('/api/orders').then(handleResponse);
                const tbody = document.getElementById('adminOrdersBody');
                tbody.innerHTML = '';
                orders.forEach(order => {
                    const tr = document.createElement('tr');
                    const techName = order.assignedTech ? order.assignedTech.username : 'N/A';
                    tr.innerHTML = `
                        <td>${order.id}</td>
                        <td>${order.customer.username}</td>
                        <td>${order.device.brand} ${order.device.model}</td>
                        <td>${order.status}</td>
                        <td>${techName}</td>
                        <td>
                            ${order.status === 'PENDING' || order.status === 'IN_PROGRESS' ?
                              `<button onclick="assignTech(${order.id})">Asignar</button>` :
                              'N/A'}
                        </td>
                         <td>
                            ${order.status !== 'CANCELED' && order.status !== 'DELIVERED' ?
                              `<button onclick="cancelOrderAsAdmin(${order.id})">Cancelar</button>` :
                              'N/A'}
                        </td>
                    `;
                    tbody.appendChild(tr);
                });
            } catch (e) { alert('Error cargando órdenes: ' + e.message); }
        }

        async function loadTechs() {
            try {
                const users = await fetch('/api/users').then(handleResponse);
                const select = document.getElementById('techSelect');
                users.filter(u => u.role === 'TECH').forEach(tech => {
                    const option = document.createElement('option');
                    option.value = tech.id;
                    option.textContent = `${tech.fullName || tech.username} (ID: ${tech.id})`;
                    select.appendChild(option);
                });
            } catch (e) { alert('Error cargando técnicos: ' + e.message); }
        }

        async function assignTech(orderId) {
            const techId = document.getElementById('techSelect').value;
            if (!techId) {
                alert('Por favor, seleccione un técnico.');
                return;
            }
            if (confirm(`¿Asignar técnico ID ${techId} a la orden ID ${orderId}?`)) {
                try {
                    await fetch(`/api/orders/${orderId}/assign/${techId}`, { method: 'PUT' }).then(handleResponse);
                    loadAllOrders();
                } catch (e) { alert('Error al asignar: ' + e.message); }
            }
        }

        async function cancelOrderAsAdmin(orderId) {
             if (confirm('¿Seguro que quiere CANCELAR (Admin) esta orden?')) {
                try {
                    // 🚨 CAMBIO CLAVE: Usamos la nueva ruta DELETE con el ACTOR_ID (Admin)
                    await fetch(`/api/orders/${orderId}/cancel/${ACTOR_ID}`, { method: 'DELETE' }).then(handleResponse);
                    loadAllOrders();
                } catch (error) {
                    alert(`Error al cancelar: ${error.message}`);
                }
             }
        }

        // --- Lógica de Dispositivos (SIN CAMBIOS) ---
        async function loadDevices() {
             // ...
        }

        function resetDeviceForm() {
             // ...
        }

        function editDevice(id, brand, model, serial) {
             // ...
        }

        async function saveDevice(event) {
             // ...
        }

        async function deleteDevice(id) {
            // ...
        }

        // --- Lógica de Usuarios (SIN CAMBIOS) ---
        async function loadUsers() {
             // ...
        }

        async function createUser(event) {
             // ...
        }

        // --- Inicialización ---
        document.addEventListener('DOMContentLoaded', () => {
            checkSession();
            loadAllOrders();
            loadTechs();
            loadDevices();
            loadUsers();

            document.getElementById('deviceForm').addEventListener('submit', saveDevice);
            document.getElementById('userForm').addEventListener('submit', createUser);
        });
    </script>
</body>
</html>