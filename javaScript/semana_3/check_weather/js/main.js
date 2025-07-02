import axios from 'axios';

document.addEventListener('DOMContentLoaded', function () {
    const API_KEY = import.meta.env.VITE_WEATHER_API;
    const inputCity = document.getElementById('typeCity');
    const resultData = document.getElementById('result');

    async function searchWeather() {
        const diffentsCity = inputCity.value.trim();

        if (!diffentsCity) {
            resultData.innerHTML = 'Empty field, please enter a city name';
            return;
        }

        const url = `https://api.openweathermap.org/data/2.5/weather?q=${diffentsCity}&appid=${API_KEY}&units=metric&lang=en`;

        try {
            const answer = await axios.get(url);
            const data = answer.data;

            const temperature = data.main.temp;
            const humidity = data.main.humidity;
            const speedWind = data.wind.speed;
            const sunrise = new Date(data.sys.sunrise * 1000).toLocaleTimeString();
            const sunset = new Date(data.sys.sunset * 1000).toLocaleTimeString();
            const { icon, description } = data.weather[0];
            // const iconUrl = `https://openweathermap.org/img/wn/${icon}@2x.png`;
            const iconUrl = `https://rodrigokamada.github.io/openweathermap/images/${icon}_t@4x.png`;

            const now = new Date();

            // Get sunrise/sunset times in Date
            const sunriseTime = new Date(data.sys.sunrise * 1000);
            const sunsetTime = new Date(data.sys.sunset * 1000);
            // Compare to find out if it is day
            const isDayTime = now >= sunriseTime && now < sunsetTime;

            // Change body class for styles
            if (isDayTime) {
                document.body.classList.remove('night');
                document.body.classList.add('day');
            } else {
                document.body.classList.remove('day');
                document.body.classList.add('night');
            }

            // resultData.innerHTML = `
            //     <h2>Weather in ${data.name}, ${data.sys.country}</h2>
            //     <img src="${iconUrl}" alt="${description}" />
            //     <p><strong>${description.toUpperCase()}</strong></p>
            //     <p><strong>Temperature:</strong> ${temperature} °C</p>
            //     <p><strong>Humidity:</strong> ${humidity}%</p>
            //     <p><strong>Wind Speed:</strong> ${speedWind} m/s</p>
            //     <p><strong>Sunrise:</strong> ${sunrise}</p>
            //     <p><strong>Sunset:</strong> ${sunset}</p>
            // `;

            resultData.innerHTML = `
                <div class="weather-card">
                    <img src="${iconUrl}" alt="${description}" class="weather-icon"/>
                    <div class="temperature">${Math.round(temperature)}°C</div>
                    <div class="city">${data.name}</div>
                    <div class="description">${description}</div>
                    <div class="extra-info">
                        <div>
                            <img src="/img/humidity.svg" alt="Humidity Icon" class="icon-extra"/>
                            <span><strong>Humidity:</strong> ${humidity}%</span>
                        </div>
                        <div>
                            <img src="/img/wind.svg" alt="Wind Icon" class="icon-extra"/>
                            <span><strong>Wind:</strong> ${speedWind} m/s</span>
                        </div>
                        <div>
                            <img src="/img/sunrise.svg" alt="Sunrise Icon" class="icon-extra"/>
                            <span><strong>Sunrise:</strong> ${sunrise}</p>
                        </div>
                        <div>
                            <img src="/img/sunset.svg" alt="Sunset Icon" class="icon-extra"/>
                            <span><strong>Sunset:</strong> ${sunset}</p>
                        </div>    
                    </div>
                </div>
            `;
            // console.log(data);
        } catch (error) {
            // console.error('Error al obtener el clima:', error.response?.data || error.message);
            if (error.response?.status === 404) {
                resultData.innerHTML = 'City not found. Try another.';
            } else {
                resultData.innerHTML = 'An error occurred. Try again.';
            }
            console.error('Error:', error);
        }

        inputCity.value = "";
    }

    inputCity.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            searchWeather();
        }
    });

    window.searchWeather = searchWeather; // Hacer la función accesible globalmente
});