# Clase de JavaScript: Objetos, Sets, Maps, Métodos, y Recorridos

## 1. Objetos

Un **objeto** en JavaScript es una colección de pares clave-valor. Se utiliza para almacenar datos estructurados.

### Ejemplo:
```js
const persona = {
  nombre: "Ana",
  edad: 25,
  ciudad: "Bogotá"
};
console.log(persona.nombre); // "Ana"
```

## 2. Sets

Un **Set** es una colección de valores únicos. No permite elementos duplicados.

### Ejemplo:
```js
const numeros = new Set();
numeros.add(1);
numeros.add(2);
numeros.add(2); // No se agrega de nuevo

console.log(numeros); // Set(2) {1, 2}
```

### Otro ejemplo:
```js
const letras = new Set(["a", "b", "c", "a"]);
console.log(letras); // Set(3) {"a", "b", "c"}
```


## 3. Maps

Un **Map** es una colección de pares clave-valor en la que las claves pueden ser de **cualquier tipo**, incluyendo objetos, funciones o cualquier otro valor primitivo. A diferencia de los objetos, los Maps mantienen el orden de inserción de los elementos.

### Ejemplo:
```js
const mapa = new Map();
mapa.set("nombre", "Carlos");
mapa.set("edad", 30);

console.log(mapa.get("nombre")); // "Carlos"
```

### Otro ejemplo con claves de diferentes tipos:
```js
const mapaVariado = new Map();
const obj = { id: 1 };
const func = function() {};
const numero = 42;

mapaVariado.set(obj, "Objeto como clave");
mapaVariado.set(func, "Función como clave");
mapaVariado.set(numero, "Número como clave");

console.log(mapaVariado.get(obj));     // "Objeto como clave"
console.log(mapaVariado.get(func));    // "Función como clave"
console.log(mapaVariado.get(numero));  // "Número como clave"
```
## 4. for...of

Se utiliza para recorrer objetos iterables como arrays, sets y maps.

### Ejemplo:
```js
const frutas = ["manzana", "pera", "uva"];
for (const fruta of frutas) {
  console.log(fruta);
}
```

## 5. for...in

Se usa para recorrer las propiedades enumerables de un objeto.

### Ejemplo:
```js
const persona = {
  nombre: "Luis",
  edad: 22
};
for (const clave in persona) {
  console.log(clave + ": " + persona[clave]);
}
```

## 6. Métodos en JS

Los **métodos** son funciones dentro de objetos.

### Ejemplo:
```js
const perro = {
  nombre: "Max",
  ladrar: function() {
    console.log("¡Guau!");
  }
};
perro.ladrar(); // ¡Guau!
```

## 7. Arrays

Un **array** es una lista ordenada de valores.

### Ejemplo:
```js
const colores = ["rojo", "verde", "azul"];
console.log(colores[1]); // "verde"
```

## 8. console.table

Muestra datos tabulados en la consola, útil para ver arrays u objetos.

### Ejemplo:
```js
const personas = [
  { nombre: "Ana", edad: 30 },
  { nombre: "Luis", edad: 25 }
];
console.table(personas);
```

## 9. forEach

`forEach` es un método de los arrays que permite ejecutar una función por cada uno de sus elementos. Es muy útil para realizar acciones como imprimir, modificar o transformar elementos. **No retorna un nuevo array** (a diferencia de `map`).

### Ejemplo:
```js
const numeros = [1, 2, 3];
numeros.forEach(function(num) {
  console.log(num * 2);
});
```

### Otro ejemplo: crear elementos HTML con forEach
```js
const frutas = ["Manzana", "Banano", "Fresa"];
const lista = document.createElement("ul");

frutas.forEach(function(fruta) {
  const item = document.createElement("li");
  item.textContent = fruta;
  lista.appendChild(item);
});

document.body.appendChild(lista); // Añade la lista al body del HTML
```

## 10. Métodos comunes para Arrays

- `push()`: Agrega un elemento al final.
- `pop()`: Elimina el último elemento.
- `shift()`: Elimina el primer elemento.
- `unshift()`: Agrega un elemento al inicio.
- `map()`: Crea un nuevo array con los resultados de la función aplicada.
- `filter()`: Filtra los elementos que cumplen cierta condición.
- `reduce()`: Aplica una función reductora a todos los elementos.
- `find()`: Encuentra el primer elemento que cumpla la condición.
- `includes()`: Verifica si un elemento existe en el array.

### Ejemplo:
```js
const numeros = [1, 2, 3, 4];
const pares = numeros.filter(n => n % 2 === 0);
console.log(pares); // [2, 4]
```


## 11. Métodos comunes para Objetos

- `Object.keys(obj)`: Devuelve un array con las claves.
- `Object.values(obj)`: Devuelve un array con los valores.
- `Object.entries(obj)`: Devuelve un array con pares [clave, valor].
- `hasOwnProperty()`: Verifica si una propiedad existe directamente en el objeto (no en su prototipo).

### Ejemplo:
```js
const persona = {
  nombre: "Sara",
  edad: 28
};

console.log(Object.keys(persona));   // ["nombre", "edad"]
console.log(Object.values(persona)); // ["Sara", 28"]

console.log(persona.hasOwnProperty("edad"));     // true
console.log(persona.hasOwnProperty("apellido")); // false
```
## 12. Método .map()

El método `.map()` crea un **nuevo array** con los resultados de aplicar una función a **cada elemento** del array original. Es muy útil cuando se desea transformar los elementos de un array sin modificar el original.

### Sintaxis:
```js
array.map(function(elemento, indice, array) {
  // retornar nuevo elemento
});
```

### Ejemplo 1: multiplicar números
```js
const numeros = [1, 2, 3];
const dobles = numeros.map(num => num * 2);
console.log(dobles); // [2, 4, 6]
```

### Ejemplo 2: obtener nombres de objetos
```js
const personas = [
  { nombre: "Ana", edad: 30 },
  { nombre: "Luis", edad: 25 }
];
const nombres = personas.map(p => p.nombre);
console.log(nombres); // ["Ana", "Luis"]
```

### Ejemplo 3: transformar strings
```js
const frutas = ["manzana", "pera", "uva"];
const mayusculas = frutas.map(fruta => fruta.toUpperCase());
console.log(mayusculas); // ["MANZANA", "PERA", "UVA"]
```
