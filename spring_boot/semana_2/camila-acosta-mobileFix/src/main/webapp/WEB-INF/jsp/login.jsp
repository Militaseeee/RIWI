<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MobileFix - Iniciar Sesión</title>
    <style>
        body { font-family: sans-serif; display: flex; flex-direction: column; align-items: center; padding-top: 50px; }
        #loginForm { border: 1px solid #ccc; padding: 30px; border-radius: 8px; width: 300px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #333; }
        div { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="number"], select { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
        button:hover { background-color: #0056b3; }
        hr { width: 100%; margin: 20px 0; border-top: 1px solid #eee; }
        p { margin-top: 0; font-size: 14px; text-align: center; background-color: #f8f9fa; padding: 10px; border-radius: 4px; }
        p b { font-weight: bold; color: #333; }
    </style>
</head>
<body>
    <form id="loginForm">
        <h2>Bienvenido a MobileFix</h2>

        <div>
            <label for="id">ID de Usuario:</label>
            <input type="number" id="id" placeholder="ID de Admin/Tech/User" required autofocus>
        </div>
        <div>
            <label for="role">Rol a Simular:</label>
            <select id="role">
                <option value="ADMIN">ADMIN (ID 1)</option>
                <option value="TECH">TECH (ID 2)</option>
                <option value="USER">USER (ID 3)</option>
            </select>
        </div>

        <button type="submit">Entrar (Simular)</button>
    </form>

    <hr>
    <p><b>Usuarios de prueba (data.sql):</b><br>
       ID: 1 / Rol: ADMIN<br>
       ID: 2 / Rol: TECH<br>
       ID: 3 / Rol: USER
    </p>

    <script>
        document.getElementById('loginForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const userId = document.getElementById('id').value;
            const userRole = document.getElementById('role').value;

            if (userId) {
                // 1. Almacenar el ID y el Rol en el navegador para simular la sesión
                localStorage.setItem('userId', userId);
                localStorage.setItem('userRole', userRole);

                // 2. Determinar la URL de redirección final
                // ESTAS RUTAS DEBEN COINCIDIR CON EL ViewControler
                let dashboardUrl = '';
                if (userRole === 'ADMIN') {
                    dashboardUrl = '/admin_dashboard';
                } else if (userRole === 'TECH') {
                    dashboardUrl = '/tech_dashboard';
                } else if (userRole === 'USER') {
                    dashboardUrl = '/user_dashboard';
                }

                // 3. Redirigir al dashboard correcto
                if (dashboardUrl) {
                    window.location.href = dashboardUrl;
                } else {
                    alert('Error: Rol no reconocido.');
                }
            } else {
                alert('Por favor, ingrese un ID de usuario.');
            }
        });
    </script>
</body>
</html>
