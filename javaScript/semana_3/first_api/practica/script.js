// --> cd practica/
// --> node script.js 

// fetch('http://localhost:3000/usuarios')
//   .then(res => res.json())
//   .then(data => console.log(data));


// --> install: npm install axios

import axios from 'axios';

axios.get('https://jsonplaceholder.typicode.com/posts/1')
  .then(response => console.log(response.data))
  .catch(error => console.error(error));

// --> node script.js 