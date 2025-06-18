/* Calculadora de Promedio de Notas
 
Enunciado:
Crea un formulario con 5 campos numéricos (una nota por cada uno) y un botón “Calcular Promedio”. Al hacer clic:
 
    - Una función debe tomar los valores, sumarlos en un for.
    - Calcular el promedio y mostrarlo en el DOM.
    - Usar una condicional para mostrar si el estudiante aprobó (promedio ≥ 3.0) o reprobó.
    - usar validación de solo numeros en los campos de las notas. */

function calculateAverage() {

    let putNote1 = document.getElementById('note1').value;
    let putNote2 = document.getElementById('note2').value;
    let putNote3 = document.getElementById('note3').value;
    let putNote4 = document.getElementById('note4').value;
    let putNote5 = document.getElementById('note5').value;

    const notesAdd = [putNote1, putNote2, putNote3, putNote4, putNote5];
    console.log(notesAdd);

    let valNumbers = true;

    for (let assess = 0; assess < notesAdd.length; assess++) {
        if (typeof(notesAdd[assess]) !== "number") {
            valNumbers = false;
        }
    }

    if(valNumbers){
        putNote1 = parseFloat(putNote1)
        putNote2 = parseFloat(putNote2)
        putNote3 = parseFloat(putNote3)
        putNote4 = parseFloat(putNote4)
        putNote5 = parseFloat(putNote5)

        let count = 0
    
        for (let index = 0; index < notesAdd.length; index++) {
            count += notesAdd[index]
        }
    
        let average = count / notesAdd.length
        let averageMessage = 'The average is: ' + average
        
        const averageNotes = document.getElementById('average').textContent = averageMessage
    
        let approveMessage = '';
    
        if (average >= 3) {
            approveMessage = 'Congratulation c: <3'
            
        } else {
           approveMessage = 'Ohhhh men, you lose :c'
        }
    
        document.getElementById('message').textContent = approveMessage
    } else {
        alert('Todos los datos deben ser números')
    }
}

function deleteInfo() {
    document.getElementById('note1').value = ""; 
    document.getElementById('note2').value = ""; 
    document.getElementById('note3').value = ""; 
    document.getElementById('note4').value = ""; 
    document.getElementById('note5').value = ""; 
    document.getElementById('average').textContent = "";
    document.getElementById('message').textContent = "";
}
