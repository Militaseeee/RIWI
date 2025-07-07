let count = 1; 
const button = document.getElementById('pressButton');
const ulAddElement = document.getElementById('addElement');

button.addEventListener('click', function() {
    const liAddCount = document.createElement('li'); 
    liAddCount.textContent = 'Element ' + count; 
    ulAddElement.appendChild(liAddCount);
    count++;
});