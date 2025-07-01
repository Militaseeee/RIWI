
#  Clase de JavaScript Intermedio: Promesas, Async/Await, Clases y Modularidad

---

##  1. Promesas

###  Teoría

Una **promesa** representa un valor que puede estar disponible ahora, en el futuro o nunca. Tiene tres estados:
- **Pending (pendiente)**
- **Fulfilled (cumplida)**
- **Rejected (rechazada)**

```js
const promesa = new Promise((resolve, reject) => {
  const exito = true;
  if (exito) {
    resolve("Todo salió bien!");
  } else {
    reject("Ocurrió un error.");
  }
});
```

###  Ejemplo 1: Promesa básica

```js
const promesa = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve("Resultado después de 2 segundos");
  }, 2000);
});

promesa.then(mensaje => console.log(mensaje));
```

###  Ejemplo 2: Fetch con then/catch (API real)

```js
fetch("https://jsonplaceholder.typicode.com/posts/1")
  .then(res => res.json())
  .then(data => console.log("Post:", data))
  .catch(err => console.error("Error:", err));
```

###  Ejercicios

1. Usa `fetch` para obtener los comentarios de un post: `https://jsonplaceholder.typicode.com/comments?postId=1`.
2. Muestra en consola solo los emails de los comentarios.

---

##  2. Async / Await

###  Teoría

Una forma más clara y estructurada de trabajar con Promesas.

```js
async function obtenerPost() {
  try {
    const res = await fetch("https://jsonplaceholder.typicode.com/posts/2");
    const data = await res.json();
    console.log(data);
  } catch (err) {
    console.error("Error:", err);
  }
}
```

###  Ejemplo 1: Fetch con async/await

```js
async function obtenerUsuarios() {
  const respuesta = await fetch("https://jsonplaceholder.typicode.com/users");
  const usuarios = await respuesta.json();
  console.log("Usuarios:", usuarios);
}

obtenerUsuarios();
```

###  Ejemplo 2: Error controlado

```js
async function obtenerDatos() {
  try {
    const res = await fetch("https://api.fakeurl.com/data");
    if (!res.ok) throw new Error("Respuesta no válida");
    const datos = await res.json();
    console.log(datos);
  } catch (error) {
    console.error("Hubo un error:", error.message);
  }
}
```

### 💪 Ejercicio

1. Crea una función `obtenerUsuario(id)` que reciba un id y muestre los datos del usuario desde `https://jsonplaceholder.typicode.com/users/{id}` usando `async/await`.

---

##  3. Clases

###  Teoría

Las clases son plantillas para crear objetos. Es azúcar sintáctico sobre funciones constructoras.

```js
class Persona {
  constructor(nombre, edad) {
    this.nombre = nombre;
    this.edad = edad;
  }

  saludar() {
    console.log(`Hola, soy ${this.nombre} y tengo ${this.edad} años`);
  }
}

const p = new Persona("Juan", 30);
p.saludar();
```

###  Ejemplo 1: Clase Producto

```js
class Producto {
  constructor(nombre, precio) {
    this.nombre = nombre;
    this.precio = precio;
  }

  mostrarInfo() {
    console.log(`Producto: ${this.nombre}, Precio: $${this.precio}`);
  }
}

const p = new Producto("Camisa", 25000);
p.mostrarInfo();
```

###  Ejemplo 2: Herencia

```js
class Animal {
  constructor(nombre) {
    this.nombre = nombre;
  }

  hablar() {
    console.log(`${this.nombre} hace un sonido`);
  }
}

class Perro extends Animal {
  hablar() {
    console.log(`${this.nombre} dice: guau!`);
  }
}

const miPerro = new Perro("Firulais");
miPerro.hablar();
```

### 💪 Ejercicio

1. Crea una clase `Vehiculo` con propiedades `marca`, `modelo` y método `mostrarDetalles()`.
2. Extiende la clase a `Moto` con una propiedad `cilindraje`.

---

##  4. Modularidad (ESModules)

###  Teoría

Permite dividir el código en múltiples archivos reutilizables. Usamos `export` y `import`.

###  Ejemplo 1: Archivos separados

#### archivo: `saludos.js`

```js
export function saludar(nombre) {
  return `Hola, ${nombre}`;
}
```

#### archivo: `main.js`

```js
import { saludar } from './saludos.js';

console.log(saludar("Ana"));
```

###  Ejemplo 2: Export default

#### archivo: `math.js`

```js
export default function sumar(a, b) {
  return a + b;
}
```

#### archivo: `main.js`

```js
import sumar from './math.js';

console.log(sumar(5, 3));
```

### 💪 Ejercicio

1. Crea un archivo `math.js` que exporte funciones `suma`, `resta`, `multiplicacion`, `division`.
2. Importa esas funciones desde `main.js` y prueba cada una.

### 📝 Nota:
Si se usa en el navegador, no olvides:

```html
<script type="module" src="main.js"></script>
```

---

## 🧩 Reto Final (Integra Todo)

1. Crea una clase `PostService` que use `fetch` para obtener posts.
2. Usa `async/await` para manejar las peticiones.
3. Divide el código en módulos: uno para la clase, otro para ejecutar.
4. Muestra el título de los 5 primeros posts en consola.
