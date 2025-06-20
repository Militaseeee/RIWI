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

    if (nameUser.trim() === "" || isNaN(age) || age.trim() === "" || age < 0) {

        showToast("Please enter both your name and a valid age  ");
        // console.log("ERROR!!! Please, enter a valid age in number !!!")
        return;
    } 

    let ageCheck = parseInt(age);

    if (ageCheck >= 0 && ageCheck <= 5) {
        showToast(`Hi ${nameUser}, you are a baby! 👶`, "#ffccbc");
    } else if (ageCheck >= 6 && ageCheck <= 10) {
        showToast(`Hi ${nameUser}, you are very young! Study a lot to be a great student. 📚`, "#ffd54f");
    } else if (ageCheck >= 11 && ageCheck <= 15) {
        showToast(`Hi ${nameUser}, if you want to be a programmer, you can watch videos to learn what it is. 💡`, "#4fc3f7");
    } else if (ageCheck >= 16 && ageCheck < 18) {
        showToast(`Hi ${nameUser}, you are close to 18. Keep learning and practicing. 💻`, "#81c784");
    } else if (ageCheck >= 18) {
        showToast(`Hi ${nameUser}, you are an adult. Programming can help you build a good future! 🚀`, "#b39ddb");
    }
}


function deleteInfo() {
    document.getElementById('nameUser').value = "";
    document.getElementById('age').value = "";
}


function showToast(message, backgroundColor = "#ff5f6d") {
    Toastify({
        text: message,
        duration: 4000,
        gravity: "top", 
        position: "center",
        close: true,
        style: {
            background: backgroundColor,
            color: "#000",
            borderRadius: "8px",
            padding: "10px 5px"
        }
    }).showToast();
}