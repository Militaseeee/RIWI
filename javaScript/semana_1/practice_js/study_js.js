/* imprimir en cosola */
console.log("Hola mundo");

/* Definir la variable -> let */
let nombre = "Camila";
let edad = 23;
let activo = true; /* Debe tener la primera letra en minuscula */
let newUser = false;

let nada = null
/* RECORDAR */
/* una variable que fue declarada pero no inicializada */
let indefinido;

/* El operador typeof se utiliza para comprobar el tipo de dato de una variable en el código*/
console.log(typeof nombre); /* nombre es un string */

/* Esto en Python es un diccionario, acá se llama objeto */
let persona = {nombre: "Mila", apellido: "Acosta", edad: 22, isActive: true };
//               0                 1                 2           3
let numeros = [1, 2, 3]; /* Esto es un array */ 
            // 0  1  2

/* Imprimiré el objeto persona y traeré especificamente la edad */
console.log(persona.edad)
console.log(numeros[2]) /* Me trae el #3 ya que esta en la posición 2 */

// EJEMPLO 2 CON OBJETO

let users = [
    { nombre2: "Ana", apellido: "Macias", edad: 22, isActive: true },
    { nombre2: "Luis", apellido: "Henao", edad: 22, isActive: true },
    { nombre2: "Catalina", apellido: "Macias", edad: 22, isActive: true },
    { nombre2: "Ana", apellido: "Macias", edad: 22, isActive: true },
]

/* Me traigo la info de la posicion 0 (el primer dato) y muestro apellido */
console.log(users[0].apellido);

// EJEMPLO 3 CON OBJETO

let products = {
    motos: [{ yamaha: 2 }, { kawasaki: 5 }],
};

console.log(products.motos[1]); /* Traigo todos los datos de la posición 1 */

/* CADENA INVERTIDA */

/* const se utiliza para declarar variables cuya asignación no puede ser modificada */
const coder = "Camila";

let inverted = `"Hello", ${coder}
    This is a example
    ${2024+1}`;

console.log(inverted);

/* OPERACIONES DE COMPARACIÓN */

// Operador        Descripción      Ejemplo     Resultado
//    ==       igual a              1 == '1'      true
//   ===       obligatorio igual    0 === '0'     false
/*    !=       diferente            10!= '10'     false    */
/*   !==       obligatorio dif      2!== '2'      true      */


/* CONDICIONALES */
if (edad >= 18) {
    console.log("Eres mayor de edad")
} else {
    console.log("Eres menor de edad")
}

/* CONDICIONALES CON TERNARIO */ 
let status1 = (edad >= 18) ? 'Adult' : 'Minor';
console.log(status1);


/* console.assert() */
/* Muestra un mensaje si la condición que le pasas es falsa. 
Se utiliza para hacer comprobaciones rápidas de condiciones en el código. 
Si la condición es falsa, muestra el mensaje de error, de lo contrario no se muestra nada. */

let skills = "HTML, CSS";
console.assert(skills.includes("JavaScript"),"Corder doesn't know Javascript");
/* console.assert(skills.includes("HTML"),"Corder doesn't know Javascript"); */

/* EJEMPLO 2 */
n1 = 22
n2 = '22'

if (n1 === n2) {
    console.log("Es igual")
} else {
    console.log("n1 === n2 Es diferente")
}

/* CONDICIONALES CON TERNARIO */ 
let ejemplo2 = (n1 === n2) ? 'same' : 'diferent';
console.log(ejemplo2)


/* EJEMPLO 3 */
nu1 = 22
nu2 = 50

if (nu1 !== nu2) {
    console.log("n1 !== n2 Es diferente")
} else {
    console.log("Es igual")
}

/* CONDICIONALES CON TERNARIO */ 
let ejemplo3 = (nu1 !== nu2) ? 'same' : 'diferent';
console.log(ejemplo3)


/* EJEMPLO 4 */
num1 = 22
num2 = '22'

if (num1 != num2) {
    console.log("Es diferente")
} else {
    console.log("n1 != n2 Es igual")
}

/* CONDICIONALES CON TERNARIO */ 
let ejemplo4 = (num1 != num2) ? 'Diferent' : 'Same';
console.log(ejemplo4)


let entrada = 20;
let numero1 = 10;
let numero2 = 30;

/* EJEMPLO 5 */
if (entrada < numero1) {
    console.log("Es menor que 10");
} else if ( (entrada > numero1 ) && (entrada < numero2) ){
    console.log("El valor está entre 10 y 30")
} else {
    console.log("Es mayor que 30")
}

/* CONDICIONALES CON AND Y OR */
if ( activo && newUser ) {
    console.log("Se cumple el if")
} else {
    console.log("No se cumple el if")
}

if ( activo || newUser ) {
    console.log("Se cumple el if")
} else {
    console.log("No se cumple el if")
}

/* CONDICIONALES CON TERNARIO */ 
activo == newUser ? console.log("se cumple") : console.log("no se cumple")

let act = true
let nUser = true

/* ESTE SERIA EL NOT */
// Se debe poner ! en la variable
act == !nUser ? console.log("se cumple") : console.log("no se cumple")


/* EJERCICIO CON EXPONENTE */

let resultado;
let a = 2;
let b = 10;

resultado = 2 ** 10;
console.log(resultado);


/* FUNCIONES */
function sumar() {
    let result
    let a2 = 2;
    let b = 10;
    result = a + b;
    console.log(result);
}

sumar()

/* SCOPE -> Él alcance de una función (ejemplo: let resultado esta en el scope de sumar) */


/* EJEMPLO DE FUNCIONES */
function add(value1, value2) {
    let result

    result = value1 + value2;
    console.log(result);
}

add(4, true) /* true = 1, false = 0 */
add(4, "22") /* Cuando es cadena de caracter se adjunta los números */

/* ARROW FUNCTION */
/* Ocupa menos espacio de memoria -> Es mas optimo */

const sumar2 = (value1, value2) => {
    console.log(value1 + value2)
}

sumar2(11,5)
sumar2(2,5)
sumar2(10,5)
sumar2(2,37)

/* Otra forma es con cadena invertida */
const sumar3 = (value1, value2) => {
    console.log(`Desde arrow function ${value1 + value2}`)
}

sumar3(11,5)
sumar3(2,5)