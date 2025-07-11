const nameInput = document.getElementById('name');
const ageInput = document.getElementById('age');

// Event for saving data in local storage
document.getElementById('saveButton').addEventListener('click', () => {
    enterData();
});

const enterData = () => {

    if (!nameInput || !ageInput) {
        console.error('Elements of the form don´t exist');
        return;
    }

    const name = capitalizeFirstLetter(nameInput.value.trim());
    const age = parseInt(ageInput.value);
 
    if (name && (!isNaN(age) && age > 0)) {
        localStorage.setItem('userName', name);
        localStorage.setItem('userAge', age);

        console.log(`😎 userName: ${localStorage.getItem('userName')}`);
        console.log(`🎂 userAge: ${localStorage.getItem('userAge')}`);

        updateInteractionCount();

        displayData();
        notification('Data saved successfully!', "#4BB543", 3000);
    } else {
        notification('Please enter your correct name and your numerical age', "#f54747", 3000);
    }
}

// Function for showing saved dates
const displayData = () => {
    const name = localStorage.getItem('userName');
    const age = localStorage.getItem('userAge');
    const outputDiv = document.getElementById('output');
    if (name && age) {
        outputDiv.textContent = `Hello ${name}, you are ${age} years old`;
    } else {
        outputDiv.textContent = 'No stored data found';
    }

    nameInput.value = "";
    ageInput.value = "";
}

// On page load, display stored data
window.onload = displayData;

// Initialize interaction counter in Session Storage
if (!sessionStorage.getItem('interactionCount')) {
    sessionStorage.setItem('interactionCount', 0);
}

// Update interaction counter
const updateInteractionCount = () => {
    let count = parseInt(sessionStorage.getItem('interactionCount'));
    count++;
    sessionStorage.setItem('interactionCount', count);
    console.log(`Interactions in this session: ${count}`);
}

// Assign events to the counter
document.getElementById('saveButton').addEventListener('click', updateInteractionCount);
document.getElementById('clearButton').addEventListener('click', updateInteractionCount);

// Event to clean the Local Storage data
document.getElementById('clearButton').addEventListener('click', () => {
  localStorage.clear();
  displayData();
  updateInteractionCount();
})

// Toast Notification
// Displays a custom toast message using Toastify
const notification = (text, color, duration) => {
  Toastify({
    text: text,
    duration: duration,
    close: true,
    gravity: "top",
    position: "center",
    stopOnFocus: true,
    style: {
        borderRadius: "8px",
        padding: "15px",
        background: color,
    },
  }).showToast();
};

// Capitalize First Letter 
// Capitalizes the first character of a given string
const capitalizeFirstLetter = (str) => {
  return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
};

document.getElementById('age').addEventListener('keydown', function(event) {
  if (event.key === 'Enter') {
    event.preventDefault(); 
    enterData();
  }
});