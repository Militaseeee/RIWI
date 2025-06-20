/* 
//console.log("Welcome to the interactive messaging system!");


let nameUser = prompt("Please, enter your name: ");
let age = prompt("Please, enter your age: ");

//Convert age to numeric type 
age = parseInt(age);

//This part of the code contains validation for age 
if (isNaN(age)) {
    console.log("ERROR!!! Please, enter a valid age in number !!!")
} else if ( age < 18 ) {
    alert(`Hello ${nameUser}. You are underage, keep learning and aim to meet new challenges`)
} else {
    alert(`Hello ${age}. You are of legal age, study and do a lot of programming, you have a great future with many successes`)
}
 */

function verifyAge() {
    let nameUser = document.getElementById('nameUser').value;
    let age = document.getElementById('age').value;

    if (nameUser.trim() === "" || isNaN(age) || age.trim() === "") {

        const alert = document.getElementById("alert-message");
        alert.textContent = "Please enter both your name and a valid age.";
        alert.style.display = "block"; // Mostrar el mensaje

        // Ocultar después de 3 segundos
        setTimeout(() => {
            alert.style.display = "none";
        }, 3000);
    }

    let ageCheck = parseInt(age);
    if ( ageCheck < 18 ) {
        alert(`Hello ${nameUser}. You are underage, keep learning and aim to meet new challenges`)
    } else {
        alert(`Hello ${age}. You are of legal age, study and do a lot of programming, you have a great future with many successes`)
    }
}


function deleteInfo() {
    document.getElementById('nameUser').value = "";
    document.getElementById('age').value = "";
}