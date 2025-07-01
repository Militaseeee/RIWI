import axios from 'axios';

document.addEventListener('DOMContentLoaded', function () {
    const inputCity = document.getElementById('typeCity');
    const resultData = document.getElementById('result');

    async function searchWeather() {
        const diffentsCity = inputCity.value.trim();

        if (!diffentsCity) {
            resultData.innerHTML = 'Empty field, please enter a city name';
            return;
        }

        const url = `https://api.openweathermap.org/data/2.5/weather?q=${diffentsCity}&appid=021fd002c24d9e4e63436d6f375257e4&units=metric&lang=es`;

        try {
            const answer = await axios.get(url);
            const data = answer.data;

            const temperature = data.main.temp;
            const humidity = data.main.humidity;
            const speedWind = data.wind.speed;
            const sunrise = new Date(data.sys.sunrise * 1000).toLocaleTimeString();
            const sunset = new Date(data.sys.sunset * 1000).toLocaleTimeString();

            resultData.innerHTML = 
                `
                    <h2>Weather in ${data.name}, ${data.sys.country}</h2>
                    <p><strong>Temperature:</strong> ${temperature} °C</p>
                    <p><strong>Humidity:</strong> ${humidity}%</p>
                    <p><strong>Wind Speed:</strong> ${speedWind} m/s</p>
                    <p><strong>Sunrise:</strong> ${sunrise}</p>
                    <p><strong>Sunset:</strong> ${sunset}</p>
                `;
            console.log(data);
        } catch (error) {
            console.error('Error al obtener el clima:', error.response?.data || error.message);
        }
    }

    inputCity.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            searchWeather();
        }
    });

    window.searchWeather = searchWeather; // Hacer la función accesible globalmente
});
