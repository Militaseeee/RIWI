/* ----------------- This code only shows something in the DOM -------------------- */
const consoleDom = () => {
    verifyAge() // In this line I tell verifyAge fuction 
}

/* ----------------- This code is only in console -------------------- */
const consoleCode = () => {

    alert("Welcome to the interactive messaging system!");
    console.log("Welcome to the interactive messaging system!");

    // Ask name and age, then show in console
    let nameConsole = prompt("Please, enter your name: ");
    console.log(nameConsole)

    let ageConsole = prompt("Please, enter your age: ");
    console.log(ageConsole)

    // Check if user pressed "Cancel"
    if (nameConsole === null || ageConsole === null) {
        console.error("❌ ERROR!!! Operation canceled by the user");
        alert("❌ ERROR!!! Operation canceled by the user");
        return;
    }

    //Convert age to numeric type 
    let ageCon = parseInt(ageConsole);

    //This part of the code contains validation for age 
    if (nameConsole.trim() === "" || isNaN(ageCon) || ageConsole.trim() === "" || ageCon < 0) {
        console.error("❌ ERROR!!! Please, enter a valid age in number !!!")
        alert("❌ ERROR!!! Please, enter a valid age in number !!!")
        return;
    } else if (ageCon >= 0 && ageCon <= 5) {
        alert(`Hi ${nameConsole}, you are a baby! 👶`);
        console.log(`Hi ${nameConsole}, you are a baby! 👶`);
    } else if (ageCon >= 6 && ageCon <= 10) {
        alert(`Hi ${nameConsole}, you are very young! Study a lot to be a great student. 📚`);
        console.log(`Hi ${nameConsole}, you are very young! Study a lot to be a great student. 📚`);
    } else if (ageCon >= 11 && ageCon <= 15) {
        alert(`Hi ${nameConsole}, if you want to be a programmer, you can watch videos to learn what it is. 💡`);
        console.log(`Hi ${nameConsole}, if you want to be a programmer, you can watch videos to learn what it is. 💡`);
    } else if (ageCon >= 16 && ageCon < 18) {
        alert(`Hi ${nameConsole}, you are close to 18. Keep learning and practicing. 💻`);
        console.log(`Hi ${nameConsole}, you are close to 18. Keep learning and practicing. 💻`);
    } else if (ageCon >= 18 && ageCon <= 30) {
        alert(`Hi ${nameConsole}, you are an adult. Programming can help you build a good future! 🚀`);
        console.log(`Hi ${nameConsole}, you are an adult. Programming can help you build a good future! 🚀`);
    } else if (ageCon >= 31 && ageCon < 50) {
        alert(`Hi ${nameConsole}, you are in a great stage of life! If you are curious about programming, it can be a fun and rewarding hobby. 🎯`);
        console.log(`Hi ${nameConsole}, you are in a great stage of life! If you are curious about programming, it can be a fun and rewarding hobby. 🎯`);
    } else if (ageCon > 50) {
        alert(`Hi ${nameConsole}, youare enjoying the golden years! Relax, enjoy your pension, and keep smiling. 🌞`);
        console.log(`Hi ${nameConsole}, you are enjoying the golden years! Relax, enjoy your pension, and keep smiling. 🌞`);
    }
}

/* This function contains all the logic for the DOM code */
const verifyAge = () => {
    let nameUser = document.getElementById('nameUser').value;
    let age = document.getElementById('age').value;

    if (nameUser.trim() === "" || isNaN(age) || age.trim() === "" || age < 0) {
        showToast("Please enter both your name and a valid age  ");
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
    } else if (ageCheck >= 18 && ageCheck <= 30) {
        showToast(`Hi ${nameUser}, you are an adult. Programming can help you build a good future! 🚀`, "#b39ddb");
    } else if (ageCheck >= 31 && age < 50) {
        showToast(`Hi ${nameUser}, you are in a great stage of life! If you are curious about programming, it can be a fun and rewarding hobby. 🎯`, "#80cbc4");
    } else {
        showToast(`Hi ${nameUser}, you are enjoying the golden years! Relax, enjoy your pension, and keep smiling. 🌞`, "#ffcc80");
    }

    deleteInfo();
}


/* Help me a delete the last people info */
const deleteInfo = () => {
    document.getElementById('nameUser').value = "";
    document.getElementById('age').value = "";
}

/* This function shows a message to the user and makes it look good on the page */
const showToast = (message, backgroundColor = "#ff5f6d") => {
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
