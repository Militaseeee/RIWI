# Guía Completa: Servidores, APIs, Node.js y JSON Server

## 1. Servidores

### ¿Qué es un servidor?

Un servidor es una computadora o sistema que proporciona recursos, datos o servicios a otras computadoras (clientes) a través de una red.

### Tipos de servidores

- **Servidor web**: aloja y sirve sitios web.
- **Servidor de bases de datos**: almacena y proporciona acceso a bases de datos.
- **Servidor de correo electrónico**: gestiona y envía correos electrónicos.
- **Servidor FTP**: para transferencia de archivos.
- **Servidor de archivos**: almacena y gestiona archivos compartidos.

### Diferencias entre HTTP y HTTPS

- **HTTP** (Hypertext Transfer Protocol): Comunicación sin cifrado.
- **HTTPS** (HTTP Secure): Comunicación cifrada mediante SSL/TLS, protege la información sensible.

### ¿Qué es JSON?

JSON (JavaScript Object Notation) es un formato ligero para el intercambio de datos, fácil de leer y escribir para humanos, y fácil de interpretar para máquinas.

```json
{
  "nombre": "David",
  "edad": 30,
  "ciudad": "Medellín"
}
```

### Status Codes HTTP

Los códigos de estado indican la respuesta del servidor ante una solicitud.

| Segmento | Descripción        | Ejemplos comunes                                                    |
| -------- | ------------------ | ------------------------------------------------------------------- |
| 1xx      | Informativos       | 100 Continue, 101 Switching Protocol                                |
| 2xx      | Éxito              | 200 OK, 201 Created, 204 No Content                                 |
| 3xx      | Redirección        | 301 Moved Permanently, 302 Found, 304 Not Modified                  |
| 4xx      | Error del cliente  | 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found     |
| 5xx      | Error del servidor | 500 Internal Server Error, 502 Bad Gateway, 503 Service Unavailable |

---

## 2. APIs

### ¿Qué es una API?

API (Interfaz de Programación de Aplicaciones) es un conjunto de reglas que permiten interactuar y comunicarse entre aplicaciones.

### Métodos HTTP

- **GET**: Obtener datos.
- **POST**: Crear datos nuevos.
- **PUT**: Actualizar datos existentes.
- **DELETE**: Borrar datos existentes.

### Ejemplo práctico con JavaScript (fetch)

```javascript
fetch('https://jsonplaceholder.typicode.com/posts/1')
  .then(response => response.json())
  .then(json => console.log(json));
```

### Params y Headers

- **Params**: Parámetros que se envían en la URL, común en solicitudes GET.
- **Headers**: Metadatos que se envían junto con una solicitud HTTP. Son clave-valor y permiten controlar aspectos como la autorización, el formato de los datos, etc.

Ejemplos claros de Headers:

```javascript
fetch('https://api.ejemplo.com/data', {
  method: 'POST',
  headers: {
    'Authorization': 'Bearer token123',
    'Content-Type': 'application/json',
    'Accept-Language': 'es-CO'
  },
  body: JSON.stringify({ nombre: "Ana" })
});
```

---

## 3. Node.js

### ¿Qué es Node.js?

Node.js es un entorno de ejecución para JavaScript basado en el motor V8 de Google, que permite ejecutar código JavaScript fuera del navegador.

### ¿Qué es npm?

npm (Node Package Manager) es el gestor de paquetes predeterminado para Node.js. Permite gestionar dependencias fácilmente.

### Crear y ejecutar un archivo JavaScript con Node.js

#### Paso a paso:

1. Crear un archivo llamado `hola.js`.

```javascript
// hola.js
console.log('Hola Mundo desde Node.js');
```

2. Ejecutar el archivo en terminal.

```bash
node hola.js
```

---

## 4. JSON Server

### ¿Qué es JSON Server?

JSON Server permite crear una API REST falsa con un archivo JSON rápidamente.

### Crear y levantar un JSON Server

#### Paso a paso:

1. Instalar JSON Server

```bash
npm install -g json-server
```

2. Crear un archivo `db.json`

```json
{
  "usuarios": [
    { "id": 1, "nombre": "Ana", "edad": 25 },
    { "id": 2, "nombre": "Luis", "edad": 30 }
  ]
}
```

3. Iniciar el servidor

```bash
json-server --watch db.json
```

4. Acceder mediante navegador o fetch

```bash
# Navegador
http://localhost:3000/usuarios
```

```javascript
// fetch
fetch('http://localhost:3000/usuarios')
  .then(res => res.json())
  .then(data => console.log(data));
```

### Uso de Axios para consumir APIs

Axios es una biblioteca que facilita las solicitudes HTTP.

```javascript
import axios from 'axios';

axios.get('https://jsonplaceholder.typicode.com/posts/1')
  .then(response => console.log(response.data))
  .catch(error => console.error(error));
```

---

## Ejercicios

1. **Servidor HTTP**: Investiga qué es un servidor HTTP básico y menciona sus usos comunes.
2. **Crear un archivo JSON**: Crea un archivo JSON simple con información personal y levanta un servidor JSON con ese archivo.
3. **Fetch básico**: Usa fetch para obtener datos de una API pública sencilla como jsonplaceholder.
4. **Métodos HTTP**: Describe brevemente para qué se usa cada uno de los métodos HTTP: GET, POST, PUT, DELETE.

5.https://crudcrud.com/

