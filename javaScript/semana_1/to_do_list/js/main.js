function addTask() {

    let addNewTasks = document.getElementById('newTasks').value.trim();

    if (addNewTasks.trim() === '') {
        showToast('Please enter valid information');
        return;
    } else {
        const randomColor = getRandomColor(); 
        showToast('Successfully added', randomColor);
    }

    // This part is to add in DOM
    const newElement = document.createElement('li');
    // newElement.textContent = addNewTasks;

    // Create a <span> element to hold the task text
    const span = document.createElement('span');
    span.textContent = addNewTasks;

     // Create a container for the buttons
    const buttonsContainer = document.createElement('div');
    buttonsContainer.classList.add('task-buttons');

    // Add buttons to the container
    buttonsContainer.appendChild(completeTaskButton(span));
    buttonsContainer.appendChild(deleteTaskButton(newElement));

    // Append the text and buttons container to the <li>
    newElement.appendChild(span);
    newElement.appendChild(buttonsContainer);

    // Add the new list item to the <ul> in the DOM
    document.querySelector('ul').appendChild(newElement);

    // Clear the input field after adding the task
    document.getElementById('newTasks').value = '';
    taskCounter();


    saveTaskLocalStorage();
}

function completeTaskButton(spanElement, completed = false) {
    const button = document.createElement('button');
    button.textContent = completed ? '❌' : '✅';
    
    button.onclick = () => {
        const taskNecessary = spanElement.classList.toggle('complete-button');
        button.textContent = taskNecessary ? '❌' : '✅';
        taskCounter();
        saveTaskLocalStorage();
    };
    return button;
}

function deleteTaskButton(taskElement) {
    const button = document.createElement('button');
    button.textContent = '🗑️';
    
    button.onclick = () => {
        taskElement.remove();
        taskCounter();
        saveTaskLocalStorage();
    };
    return button;
}

function taskCounter() {
    const tasks = document.querySelectorAll('ul li span');
    let toDo = 0;

    for (let i = 0; i < tasks.length; i++) {
        if (!tasks[i].classList.contains('complete-button')) {
            toDo++;
        }
    }
    document.getElementById('message').textContent = toDo;
}

/* Function to show messages with random background colors */
function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

/* This function shows a message to the user and makes it look good on the page */
const showToast = (message, backgroundColor = "#ff5f6d") => {
    Toastify({
        text: message,
        duration: 3000,
        gravity: "top", 
        position: "center",
        close: true,
        style: {
            background: backgroundColor,
            color: "#000",
            borderRadius: "8px",
            padding: "10px 10px 10px 16px"
        }
    }).showToast();
}

document.getElementById('newTasks').addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault(); // Prevents form submission or page reload
        addTask();
    }
});


function saveTaskLocalStorage () {
    const tasks = []
    document.querySelectorAll('ul li').forEach(li => {
        const text = li.querySelector('span').textContent;
        const completed = li.querySelector('span').classList.contains('complete-button');
        tasks.push({ text, completed }); // It is added if the task is completed (the text and the button)
    });
    localStorage.setItem('tasks', JSON.stringify(tasks)); // It converts it into a text string in JSON format and saves it in the browser with that key
    
    console.log(tasks)
}

// Load tasks when opening the page
function loadTasksFromLocalStorage() {
    const savedTasks = JSON.parse(localStorage.getItem('tasks')) || []; // We use this to get the saved tasks
    savedTasks.forEach(task => {
        const newElement = document.createElement('li');
        const span = document.createElement('span');
        span.textContent = task.text;
        if (task.completed) {
            span.classList.add('complete-button');
        }

        const buttonsContainer = document.createElement('div');
        buttonsContainer.classList.add('task-buttons');

        buttonsContainer.appendChild(completeTaskButton(span, task.completed));
        buttonsContainer.appendChild(deleteTaskButton(newElement));

        newElement.appendChild(span);
        newElement.appendChild(buttonsContainer);
        document.querySelector('ul').appendChild(newElement);
    });
    taskCounter();
}

// Load saved tasks on startup
window.addEventListener('DOMContentLoaded', loadTasksFromLocalStorage);
