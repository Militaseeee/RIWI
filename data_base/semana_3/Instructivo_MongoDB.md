# Instructivo para crear y conectar un cluster en MongoDB Atlas con MongoDB Compass

## 1. Ingresar a MongoDB Atlas
Ve a [https://www.mongodb.com/](https://www.mongodb.com/) y haz clic en **Get Started**.

## 2. Crear o iniciar sesión en tu cuenta
- Si ya tienes cuenta, inicia sesión.  
- Si no tienes cuenta, regístrate y acepta los **Privacy Policy & Terms of Service**.  

## 3. Saltar personalización
Si te pregunta sobre preferencias o personalización, puedes omitir este paso.

## 4. Crear un cluster gratuito
- Selecciona la opción **Free** (M0) para un cluster gratuito.  
- Ingresa un nombre para tu cluster (por ejemplo: `Cluster0`).  
- Haz clic en **Create Deployment**.

## 5. Configurar usuario y contraseña
- Ve a la opción **Database Access**.  
- Crea un usuario con **username** y **password**.  
- Guarda estos datos, los necesitarás para conectarte.

## 6. Crear una base de datos inicial
- En el menú **Collections**, crea una nueva base de datos y colección para iniciar.

## 7. Obtener cadena de conexión (Connection String)
- Haz clic en **Choose a Connection Method**.  
- Selecciona **Compass**.  
- Marca **I have MongoDB Compass installed**.  
- Copia el **connection string**.  
- Si es necesario, reemplaza `<password>` con la contraseña del usuario que creaste.

## 8. Descargar e instalar MongoDB Compass
- Descárgalo desde: [https://www.mongodb.com/try/download/compass](https://www.mongodb.com/try/download/compass)  
- Instálalo en tu computadora.

## 9. Conectarse desde Compass
- Abre MongoDB Compass.  
- Pega el connection string en el campo de conexión.  
- Reemplaza `<password>` con tu contraseña real.  
- Haz clic en **Connect**.
