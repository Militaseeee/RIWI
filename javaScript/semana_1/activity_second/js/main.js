/* Calculadora de Promedio de Notas

Enunciado:
Crea un formulario con 5 campos numéricos (una nota por cada uno) y un botón “Calcular Promedio”. Al hacer clic:

    - Una función debe tomar los valores, sumarlos en un for.
    - Calcular el promedio y mostrarlo en el DOM.
    - Usar una condicional para mostrar si el estudiante aprobó (promedio ≥ 3.0) o reprobó.
    - usar validación de solo numeros en los campos de las notas. */

function calculateAverage() {

    // Gets the values entered in the 5 input fields
    // and stores them in an array for easier processing
    let putNote1 = document.getElementById('note1').value;
    let putNote2 = document.getElementById('note2').value;
    let putNote3 = document.getElementById('note3').value;
    let putNote4 = document.getElementById('note4').value;
    let putNote5 = document.getElementById('note5').value;

    const notesInputs = [putNote1, putNote2, putNote3, putNote4, putNote5];

    // Checks that all values are valid numbers
    let valNumbers = true;
    for (let assess = 0; assess < notesInputs.length; assess++) {
        if (isNaN(notesInputs[assess]) || notesInputs[assess].trim() === "" || notesInputs[assess] < 0 || notesInputs[assess] > 5) {
            valNumbers = false;
        }
    }

    // If all data is valid, convert the text to decimal numbers
    // and store them in a new array called notesAdd
    if(valNumbers){
        putNote1 = parseFloat(putNote1)
        putNote2 = parseFloat(putNote2)
        putNote3 = parseFloat(putNote3)
        putNote4 = parseFloat(putNote4)
        putNote5 = parseFloat(putNote5)

        const notesAdd = [putNote1, putNote2, putNote3, putNote4, putNote5];
        console.log(notesAdd);

        // Adds all grades and calculates the average.
        // Then displays the result in the span with id "average"
        let count = 0
        for (let index = 0; index < notesAdd.length; index++) {
            count += notesAdd[index]
        }
    
        let average = count / notesAdd.length
        let averageMessage = 'The average is: ' + average.toFixed(2);
        
        const averageNotes = document.getElementById('average').textContent = averageMessage
    
        // Determines if the average is enough to pass (≥ 3)
        let approveMessage = '';
        if (average >= 3) {
            approveMessage = 'Congratulation 👊🏽🔥'
            
        } else {
            approveMessage = 'Ohhhh men, you lose 😔'
        }
    
        document.getElementById('message').textContent = approveMessage
    } else {
        alert('All data should be numbers and between 0 to 5')
    }
}

// This function clears the input values and resets the average and result messages
function deleteInfo() {
    document.getElementById('note1').value = ""; 
    document.getElementById('note2').value = ""; 
    document.getElementById('note3').value = ""; 
    document.getElementById('note4').value = ""; 
    document.getElementById('note5').value = ""; 
    document.getElementById('average').textContent = "The average is: ";
    document.getElementById('message').textContent = "";
}
