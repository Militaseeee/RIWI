# Clase de JavaScript: Hoisting, Closures, Callbacks, Scope y This

---

##  1. Hoisting

###  Teoría

**Hoisting** es el comportamiento de JavaScript que mueve las declaraciones de variables y funciones al inicio de su contexto de ejecución.

- Las **declaraciones** con `var` son elevadas e inicializadas como `undefined`.
- Las **declaraciones** con `let` y `const` también son elevadas pero quedan en una zona muerta temporal.
- Las **funciones declaradas** son completamente elevadas.

###  Ejemplos:

```js
console.log(a); // undefined
var a = 10;
```

```js
console.log(b); // ReferenceError
let b = 20;
```

```js
saludar(); // Hola!

function saludar() {
  console.log("Hola!");
}
```

```js
saluda(); // TypeError: saluda is not a function

var saluda = function () {
  console.log("Hola!");
};
```

---

## 2. Closures

###  Teoría

Un **closure** es una función que recuerda el contexto en el que fue creada. Puede acceder a variables definidas fuera de ella incluso después de que el contexto haya terminado.

###  Ejemplos:

```js
function crearContador() {
  let count = 0;
  return function () {
    count++;
    console.log(count);
  };
}

const contar = crearContador();
contar(); // 1
contar(); // 2
```

```js
function crearSaludo(saludo) {
  return function (nombre) {
    console.log(`${saludo}, ${nombre}`);
  };
}

const saludarHola = crearSaludo("Hola");
saludarHola("Juan"); // Hola, Juan
```

```js
function cuentaBancaria() {
  let saldo = 1000;

  return {
    consultar: () => saldo,
    depositar: (monto) => saldo += monto,
    retirar: (monto) => saldo -= monto
  };
}

const cuenta = cuentaBancaria();
console.log(cuenta.consultar()); // 1000
cuenta.depositar(500);
console.log(cuenta.consultar()); // 1500
```

---

##  3. Callbacks

###  Teoría

Un **callback** es una función que se pasa como argumento a otra para ser llamada luego. Es fundamental para programación asincrónica en JS.

###  Ejemplos:

```js
function procesarEntrada(input, callback) {
  const nombre = input.toUpperCase();
  callback(nombre);
}

procesarEntrada("david", function (nombreProcesado) {
  console.log("Hola, " + nombreProcesado);
});
```

```js
setTimeout(function () {
  console.log("Mensaje después de 2 segundos");
}, 2000);
```

```js
function operacion(a, b, callback) {
  return callback(a, b);
}

const resultado = operacion(5, 3, function (x, y) {
  return x + y;
});

console.log(resultado); // 8
```

```js
function obtenerUsuario(id, callback) {
  setTimeout(() => {
    callback({ id, nombre: "Ana" });
  }, 1000);
}

obtenerUsuario(1, function (usuario) {
  console.log("Usuario recibido:", usuario);
});
```

---

##  4. Scope

###  Teoría

El **scope** determina la accesibilidad de variables:

- **Global Scope**: visibles en todo el script.
- **Function Scope**: visibles solo dentro de funciones.
- **Block Scope**: `let` y `const` dentro de bloques `{}`.

###  Ejemplos:

```js
var animal = "Perro";

function mostrarAnimal() {
  var animal = "Gato";
  console.log(animal); // Gato
}

mostrarAnimal();
console.log(animal); // Perro
```

```js
if (true) {
  let mensaje = "Hola desde el bloque";
  console.log(mensaje);
}
// console.log(mensaje); // ReferenceError
```

```js
for (let i = 0; i < 3; i++) {
  setTimeout(() => {
    console.log(i); // 0, 1, 2
  }, 100);
}
```

---

##  5. This

###  Teoría

`this` es una palabra clave que hace referencia al contexto de ejecución:

- En un método, apunta al objeto.
- En funciones normales, depende del modo (estricto o no).
- En funciones flecha, hereda el contexto padre.

###  Ejemplos:

```js
const persona = {
  nombre: "Ana",
  saludar: function () {
    console.log("Hola, soy " + this.nombre);
  },
};

persona.saludar(); // 
```

```js
const obj = {
  nombre: "Luis",
  saludar: () => {
    console.log("Hola, soy " + this.nombre); // undefined
  },
};

obj.saludar();
```

```html
<!-- index.html -->
<button id="btn">Haz clic</button>
<script>
  document.getElementById("btn").addEventListener("click", function () {
    console.log(this); // <button> element
  });
</script>
```

---



