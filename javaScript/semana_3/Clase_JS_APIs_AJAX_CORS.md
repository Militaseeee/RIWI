
#  Clase: Consumo de APIs Externas con JavaScript - AJAX y CORS

---

##  1. ¿Qué es una API externa?

###  Teoría
- Una **API (Application Programming Interface)** permite que diferentes aplicaciones se comuniquen entre sí.
- Una **API externa** es una API desarrollada por otro servicio (como OpenWeatherMap, Pokémon API, etc.) que se puede consumir desde tu app.
- Las APIs REST generalmente utilizan HTTP y devuelven datos en formato JSON.

### 🛠️ Ejercicio práctico
Usaremos una API pública: [PokéAPI](https://pokeapi.co/)

```html
<!-- index.html -->
<input id="pokemonInput" placeholder="Nombre del Pokémon" />
<button onclick="buscarPokemon()">Buscar</button>
<pre id="resultado"></pre>

<script>
  async function buscarPokemon() {
    const nombre = document.getElementById('pokemonInput').value;
    const url = `https://pokeapi.co/api/v2/pokemon/${nombre.toLowerCase()}`;

    try {
      const respuesta = await fetch(url);
      if (!respuesta.ok) throw new Error('Pokémon no encontrado');
      const data = await respuesta.json();
      document.getElementById('resultado').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
      document.getElementById('resultado').textContent = error.message;
    }
  }
</script>
```

---

##  2. ¿Qué es AJAX?

###  Teoría
- **AJAX (Asynchronous JavaScript And XML)** permite hacer solicitudes HTTP asincrónicas desde el navegador sin recargar la página.
- Aunque el nombre menciona XML, hoy en día casi siempre se usa **JSON**.
- Se puede implementar con:
  - `XMLHttpRequest`
  - `fetch()`
  - Librerías como Axios

###  Ejercicio práctico con `fetch`
```html
<!-- index.html -->
<button onclick="obtenerDatos()">Cargar Usuarios</button>
<ul id="listaUsuarios"></ul>

<script>
  function obtenerDatos() {
    fetch('https://jsonplaceholder.typicode.com/users')
      .then(res => res.json())
      .then(data => {
        const lista = document.getElementById('listaUsuarios');
        lista.innerHTML = '';
        data.forEach(usuario => {
          const li = document.createElement('li');
          li.textContent = usuario.name;
          lista.appendChild(li);
        });
      });
  }
</script>
```



---

##  3. ¿Qué es CORS?

###  Teoría
- **CORS (Cross-Origin Resource Sharing)** es una política de seguridad del navegador que restringe las solicitudes hechas desde un origen distinto al del servidor.
- Ejemplo: Si tu app está en `http://localhost:3000` y haces un fetch a `https://api.ejemplo.com`, el navegador verifica si ese servidor permite compartir recursos.
- Si el servidor no responde con los headers CORS apropiados, el navegador bloquea la solicitud.

###  ¿Cómo se soluciona?
- El servidor debe enviar headers como:
  ```
  Access-Control-Allow-Origin: *
  ```
- Desde el cliente no se puede desactivar CORS; es responsabilidad del servidor.

###  Ejercicio para ver el error CORS
```js
fetch("https://example.com/api/restringida")
  .then(res => res.json())
  .catch(err => console.error("Error CORS:", err));
```

---

