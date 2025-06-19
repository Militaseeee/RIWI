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
    let age = parseInt(document.getElementById('age').value);

    if (nameUser.trim() === "" || isNaN(age) || age.trim() === "") {

        setTimeout(() => {
            alert("Please enter both your name and a valid age");
        }, 1000); 
    }
}


function deleteInfo() {
    document.getElementById('nameUser').value = "";
    document.getElementById('age').value = "";
}