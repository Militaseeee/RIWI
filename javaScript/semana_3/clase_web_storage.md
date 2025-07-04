# Clase de localStorage y sessionStorage en JavaScript

##  ¿Qué es el Web Storage?

El Web Storage es una API proporcionada por los navegadores web que permite almacenar datos de forma local en el navegador del usuario. Está compuesto por dos mecanismos:

- **localStorage**
- **sessionStorage**

##  Diferencias entre localStorage y sessionStorage

| Característica     | localStorage                          | sessionStorage                        |
|--------------------|----------------------------------------|----------------------------------------|
| Persistencia       | Permanente hasta que se borre manualmente | Solo durante la sesión del navegador  |
| Almacenamiento     | Hasta 5-10MB según el navegador        | Igual (pero pensado para sesiones)    |
| Compartido entre pestañas | ✅ Sí                            | ❌ No (solo en la pestaña actual)     |

---

## 🛠️ Métodos comunes

Ambos objetos (`localStorage` y `sessionStorage`) tienen los mismos métodos:

```js
// Guardar un dato
localStorage.setItem("clave", "valor");

// Obtener un dato
const valor = localStorage.getItem("clave");

// Eliminar un dato
localStorage.removeItem("clave");

// Limpiar todo el almacenamiento
localStorage.clear();
```

---

##  Ejemplo práctico con localStorage

Supongamos que queremos guardar el nombre del usuario en localStorage y mostrarlo al recargar la página.

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>localStorage Demo</title>
</head>
<body>
  <h1>Hola <span id="nombreGuardado">invitado</span></h1>
  <input type="text" id="nombreInput" placeholder="Escribe tu nombre">
  <button onclick="guardarNombre()">Guardar Nombre</button>

  <script>
    function guardarNombre() {
      const nombre = document.getElementById("nombreInput").value;
      localStorage.setItem("nombre", nombre);
      document.getElementById("nombreGuardado").textContent = nombre;
    }

    // Mostrar nombre guardado al cargar
    window.onload = function () {
      const nombre = localStorage.getItem("nombre");
      if (nombre) {
        document.getElementById("nombreGuardado").textContent = nombre;
      }
    };
  </script>
</body>
</html>
```

---

##  Ejemplo práctico con sessionStorage

Aquí un ejemplo para contar cuántas veces se ha hecho clic en un botón durante la sesión:

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>sessionStorage Demo</title>
</head>
<body>
  <h1>Clicks en esta sesión: <span id="contador">0</span></h1>
  <button onclick="contarClick()">Haz clic</button>

  <script>
    function contarClick() {
      let contador = sessionStorage.getItem("clicks") || 0;
      contador = parseInt(contador) + 1;
      sessionStorage.setItem("clicks", contador);
      document.getElementById("contador").textContent = contador;
    }

    // Mostrar valor actual al cargar
    window.onload = function () {
      const contador = sessionStorage.getItem("clicks") || 0;
      document.getElementById("contador").textContent = contador;
    };
  </script>
</body>
</html>
```

---

##  Consideraciones de seguridad

- No guardar contraseñas ni información sensible.
- Los datos almacenados pueden ser manipulados por el usuario.
- No sustituye una base de datos, solo sirve para almacenamiento temporal o de conveniencia.

---

##  Buenas prácticas

- Usar `try/catch` si no estás seguro del soporte del navegador.
- Serializar objetos con `JSON.stringify` y deserializar con `JSON.parse`.

```js
// Guardar un objeto
const usuario = { nombre: "Ana", edad: 28 };
localStorage.setItem("usuario", JSON.stringify(usuario));

// Leer el objeto
const usuarioGuardado = JSON.parse(localStorage.getItem("usuario"));
```

---

##  Ejercicio propuesto

1. Crea una mini app que permita agregar tareas (como una lista ToDo).
2. Las tareas deben guardarse en `localStorage` para que no se pierdan al recargar.
3. Permitir eliminar tareas.
4. Mostrar un mensaje si no hay tareas aún.

---

¡Explora y juega con Web Storage para mejorar la experiencia del usuario en tus aplicaciones web!
