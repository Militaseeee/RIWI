function addTask() {

    let addNewTasks = document.getElementById('newTasks').value;

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
}

function completeTaskButton(spanElement) {
    const button = document.createElement('button');
    button.textContent = '✅';
    
    button.onclick = () => {
        spanElement.classList.add('complete-button');
        taskCounter();
    };
    return button;
}

function deleteTaskButton(taskElement) {
    const button = document.createElement('button');
    button.textContent = '🗑️';
    
    button.onclick = () => {
        taskElement.remove();
        taskCounter();
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