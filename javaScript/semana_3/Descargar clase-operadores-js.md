
# Clase: Operadores en JavaScript

En esta clase revisaremos operadores esenciales en JavaScript que nos ayudan a tomar decisiones, verificar condiciones, y controlar el flujo de un programa.

---

## 1. Operador Ternario `? :`

### Teoría

El operador ternario es una forma abreviada de un `if...else`.

### Sintaxis

```js
condición ? valor_si_verdadero : valor_si_falso;
```

### Ejemplo

```js
const edad = 18;
const puedeEntrar = edad >= 18 ? "Sí puede entrar" : "No puede entrar";
console.log(puedeEntrar); // Sí puede entrar
```

---

## 2. Operador de Coalescencia Nula `??`

### Teoría

Devuelve el operando de la derecha si el de la izquierda es `null` o `undefined`.

### Ejemplo

```js
const nombre = null;
const nombreUsuario = nombre ?? "Invitado";
console.log(nombreUsuario); // Invitado
```

---

## 3. Operador AND Lógico `&&`

### Teoría

Evalúa si ambas condiciones son verdaderas. También puede usarse para ejecutar código condicionalmente.

### Ejemplo

```js
const logueado = true;
logueado && console.log("Mostrar panel de usuario");
```

---

## 4. Operador OR Lógico `||`

### Teoría

Devuelve el primer valor verdadero. También se usa para establecer valores por defecto.

### Ejemplo

```js
const idioma = "" || "español";
console.log(idioma); // español
```

---

## 5. Operador NOT Lógico `!`

### Teoría

Niega un valor booleano, es decir, lo invierte.

### Ejemplo

```js
const activo = false;
console.log(!activo); // true
```

---

## 6. Operador de Asignación `=`

### Teoría

Asigna un valor a una variable.

```js
let nombre = "Laura";
```

---

## 7. Operadores de Comparación

- `==` igualdad (con conversión de tipo)
- `===` igualdad estricta
- `!=` desigualdad
- `!==` desigualdad estricta
- `>` mayor que
- `<` menor que
- `>=` mayor o igual
- `<=` menor o igual

### Ejemplo

```js
console.log(5 == "5");   // true
console.log(5 === "5");  // false
```

---

## 8. Operadores Aritméticos

- `+` suma
- `-` resta
- `*` multiplicación
- `/` división
- `%` módulo (residuo)
- `**` exponenciación

### Ejemplo

```js
console.log(2 + 3);  // 5
console.log(5 % 2);  // 1
console.log(2 ** 3); // 8
```

---

## 9. Operadores de Asignación Compuesta

- `+=`, `-=`, `*=`, `/=`, `%=` etc.

```js
let puntos = 10;
puntos += 5; // puntos = 15
```

---

## 10. Operador de Encadenamiento Opcional `?.`

Permite acceder a propiedades anidadas sin causar errores si alguna propiedad es `null` o `undefined`.

```js
const persona = { nombre: "Ana" };
console.log(persona.direccion?.ciudad); // undefined
```

---

## Conclusión

Los operadores en JavaScript son herramientas clave para tomar decisiones, manipular valores y controlar el flujo del programa. Es fundamental dominarlos para escribir código claro y eficiente.
