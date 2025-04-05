async function fetchWeather(city, date) {
    const apiKey = 'ac80d6c7ed4740e78734ba80afbf2bdf'; // Your Weatherbit API key
    const url = `https://api.weatherbit.io/v2.0/forecast/daily?city=${city}&key=${apiKey}&units=M`;

    try {
        const response = await fetch(url);
        const data = await response.json();

        if (!data.data || data.data.length === 0) {
            throw new Error('No weather data found.');
        }

        // Find the weather matching the selected date
        const selectedWeather = data.data.find(day => day.datetime === date);

        if (!selectedWeather) {
            throw new Error('No weather data available for the selected date.');
        }

        return {
            temperature: selectedWeather.temp,
            condition: selectedWeather.weather.description,
            humidity: selectedWeather.rh,
            windSpeed: selectedWeather.wind_spd
        };
    } catch (error) {
        console.error('Error fetching weather data:', error);
        alert('Error fetching weather data. Please check the city name or date and try again.');
    }
}

function displayWeather(weather) {
    document.getElementById('temperature').textContent = weather.temperature;
    document.getElementById('weather-condition').textContent = weather.condition;
    document.getElementById('humidity').textContent = weather.humidity;
    document.getElementById('wind-speed').textContent = weather.windSpeed;
    document.getElementById('weather-info').style.display = 'block';
}
