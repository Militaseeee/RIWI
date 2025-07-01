# Introducción: Partes de una URL y su uso en APIs

## ¿Qué es una URL?

Una **URL** (Uniform Resource Locator) es la dirección que usamos para acceder a recursos en la web. Es como la dirección de una casa, pero para encontrar una página, una imagen o un servicio (como una API) en Internet.

---

## Partes de una URL

Veamos el siguiente ejemplo:

```
https://api.ejemplo.com:443/usuarios/123?activo=true#seccion
```

| Parte                        | Ejemplo              | ¿Qué es?                                                                 |
|-----------------------------|----------------------|--------------------------------------------------------------------------|
| **1. Protocolo**            | `https`              | Define cómo se conecta el navegador o la app. APIs usan `https` por seguridad. |
| **2. Dominio**              | `api.ejemplo.com`    | Es el nombre del servidor donde está alojada la API.                     |
| **3. Puerto (opcional)**    | `:443`               | Indica el puerto del servidor. `443` es el puerto estándar de HTTPS.    |
| **4. Ruta o Path**          | `/usuarios/123`      | Es el recurso específico que queremos consultar, por ejemplo, un usuario. |
| **5. Parámetros de consulta (Query Params)** | `?activo=true` | Son datos adicionales que se envían al servidor para filtrar o modificar la respuesta. |
| **6. Fragmento (no usado en APIs)** | `#seccion`      | Se usa en navegación de páginas web. En APIs, no tiene utilidad.        |

---

## ¿Cómo se usa una URL en una API?

Las APIs nos permiten acceder a datos desde otras aplicaciones. Usamos URLs para indicar qué información queremos.

### Ejemplo:

Supongamos que usamos la API del clima:

```
https://api.clima.com/v1/ciudades/medellin?unidad=metric&lang=es
```

- **Dominio:** `api.clima.com`
- **Ruta:** `/v1/ciudades/medellin` → Pedimos el clima de Medellín
- **Query Params:** 
  - `unidad=metric` → Queremos los datos en grados Celsius.
  - `lang=es` → Queremos la respuesta en español.

---

## ¿Qué son los Headers?

Aunque no van en la URL, los **headers** se usan en APIs para enviar información adicional, como autenticación o formato.

### Ejemplos comunes:

- `Authorization`: Se usa para enviar una API Key o token.
- `Content-Type`: Indica que estamos enviando JSON.
- `Accept`: Indica que queremos la respuesta en formato JSON.

---

## Ejemplo de uso con JavaScript (usando fetch)

```js
fetch('https://api.clima.com/v1/ciudades/medellin?unidad=metric&lang=es', {
  method: 'GET',
  headers: {
    'Authorization': 'Bearer TU_API_KEY',
    'Accept': 'application/json'
  }
})
  .then(response => response.json())
  .then(data => {
    console.log('Respuesta:', data);
  })
  .catch(error => {
    console.error('Error:', error);
  });
```

---

## Conclusión

- Una URL nos permite acceder a diferentes recursos.
- Cada parte de la URL cumple una función específica.
- En el mundo de las APIs, usamos URLs para pedir datos, enviar parámetros, y trabajar con seguridad mediante headers.

---

## Actividad sugerida

- Identifica las partes de la siguiente URL:
```
https://api.peliculas.com/v2/busqueda?titulo=matrix&idioma=es
```
- ¿Qué información está solicitando?
