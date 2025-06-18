/* Escribe un programa en JavaScript que imprima los números del 1 al 100 en el DOM, pero siguiendo estas reglas:
    • Por cada número divisible por 3, imprime “Fizz” en lugar del número.
    • Por cada número divisible por 5, imprime “Buzz” en lugar del número.
    • Para los números divisibles por ambos 3 y 5, imprime “FizzBuzz”.
    • Si el número no es divisible ni por 3 ni por 5, imprime el número normalmente. */

// Almaceno la información en una variable constante 
const putNumber = document.getElementById('numbers');

for (let index = 1; index <= 100; index++) {

    let infoText = '';

    if (index % 3 === 0 && index % 5 === 0) {
        infoText = 'FizzBuzz';
    } else if (index % 5 === 0) {
        infoText = 'Buzz';
    } else if (index % 3 === 0) {
        infoText = 'Fizz';
    } else {
        infoText = index;
    }


    // Agrego el texto al DOM
    const newElement = document.createElement('p');   // crea un "objeto" que representa el nuevo párrafo
    newElement.textContent = infoText;                // Este paso le da contenido al párrafo que acabas de crear
    putNumber.appendChild(newElement);                // Agrega ese párrafo al contenedor en la página
}