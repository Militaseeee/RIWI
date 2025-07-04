
# Clase: Manejo de Errores y Herramientas de Depuración en JavaScript

---

## 1. El Objeto `options` en `fetch`

### Teoría

El método `fetch` permite hacer peticiones HTTP. Además de la URL, puede recibir un segundo argumento llamado `options`, que permite configurar la solicitud (método, cabeceras, cuerpo, etc.).

### Estructura del objeto `options`:

```js
{
  method: 'GET' | 'POST' | 'PUT' | 'DELETE',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(data),
}
```

### Ejemplo práctico

```js
const data = {
  nombre: "David",
  edad: 30
};

fetch('https://jsonplaceholder.typicode.com/posts', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(data)
})
.then(res => res.json())
.then(json => console.log(json));
```

---

## 2. Manejo de errores en JS

### Teoría

El manejo de errores permite que una aplicación no se detenga ante un problema inesperado. JavaScript ofrece estructuras como `try...catch` y funciones como `throw` para gestionar errores.

### Ejemplo básico

```js
try {
  const resultado = 10 / 0;
  console.log("Resultado:", resultado);
} catch (error) {
  console.error("Ocurrió un error:", error.message);
}
```

---

## 3. Uso de `throw` para errores personalizados

### Teoría

El operador `throw` lanza un error que puede ser atrapado con `catch`.

### Ejemplo personalizado

```js
function dividir(a, b) {
  if (b === 0) {
    throw new Error("No se puede dividir por cero.");
  }
  return a / b;
}

try {
  console.log(dividir(10, 0));
} catch (error) {
  console.error("Error personalizado:", error.message);
}
```

---

## 4. Operador de encadenamiento opcional `?.`

### Teoría

Permite acceder a propiedades anidadas sin que se produzca un error si una propiedad intermedia es `null` o `undefined`.

### Ejemplo

```js
const usuario = {
  nombre: "Ana",
  direccion: {
    ciudad: "Medellín"
  }
};

console.log(usuario.direccion?.ciudad); // Medellín
console.log(usuario.contacto?.telefono); // undefined (no lanza error)
```

---

## 5. Uso del `debugger` en JS

### Teoría

`debugger` es una herramienta que pausa la ejecución del código si estás usando las DevTools del navegador.

### Ejemplo

```js
function saludar(nombre) {
  debugger; // Se detendrá aquí si abres la consola del navegador
  console.log("Hola", nombre);
}

saludar("Carlos");
```

---

## 6. Teoría y uso del `try...catch`

### Teoría

`try...catch` permite capturar errores que ocurren dentro del bloque `try` y manejarlos dentro del bloque `catch`.

### Ejemplo completo

```js
try {
  const json = '{"nombre": "Laura", "edad": 25}';
  const usuario = JSON.parse(json);
  console.log("Usuario:", usuario.nombre);
} catch (error) {
  console.error("Error al parsear JSON:", error.message);
}
```
