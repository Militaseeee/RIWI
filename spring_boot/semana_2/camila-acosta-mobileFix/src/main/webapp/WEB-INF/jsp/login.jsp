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
        /* Ajuste para input de texto y password */
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
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
            <label for="username">Usuario:</label>
            <input type="text" id="username" placeholder="admin, tech, o user" required autofocus>
        </div>
        <div>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" required>
        </div>

        <button type="submit">Entrar</button>
    </form>

    <hr>
    <p><b>Usuarios de prueba (data.sql):</b><br>
       Usuario: admin / Pass: pass<br>
       Usuario: cami_admin / Pass: 1234<br>
       Usuario: tech1 / Pass: pass<br>
       Usuario: user1 / Pass: pass
    </p>

    <script>
        document.getElementById('loginForm').addEventListener('submit', async function(event) {
            event.preventDefault();
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            try {
                // 1. Llamar a la nueva API de Login
                const response = await fetch('/api/auth/login', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username, password })
                });

                if (!response.ok) {
                    // Si el servicio devuelve 403 (Forbidden) o 400 (Bad Request)
                    const errorData = await response.json();
                    throw new Error(errorData.error || 'Credenciales inválidas');
                }

                // 2. Obtener los datos del usuario (ID y Rol) de la respuesta
                const userData = await response.json(); // Ej: { "id": 1, "role": "ADMIN", "fullName": "Admin User" }

                // 3. Almacenar el ID y el Rol en el navegador
                localStorage.setItem('userId', userData.id);
                localStorage.setItem('userRole', userData.role);

                // 4. Determinar la URL de redirección
                let dashboardUrl = '';
                if (userData.role === 'ADMIN') {
                    dashboardUrl = '/admin_dashboard';
                } else if (userData.role === 'TECH') {
                    dashboardUrl = '/tech_dashboard';
                } else if (userData.role === 'USER') {
                    dashboardUrl = '/user_dashboard';
                }

                // 5. Redirigir al dashboard correcto
                if (dashboardUrl) {
                    window.location.href = dashboardUrl;
                } else {
                    alert('Error: Rol no reconocido.');
                }

            } catch (error) {
                alert(error.message);
            }
        });
    </script>
</body>
</html>