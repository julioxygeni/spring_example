import { useState, useEffect } from 'react';

const conditionEmoji = {
  Sunny: '☀️',
  'Partly Cloudy': '⛅',
  Cloudy: '☁️',
  Overcast: '🌥️',
  Rain: '🌧️',
  Thunderstorm: '⛈️',
  Snow: '❄️',
  Haze: '🌫️',
};

function App() {
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState('');
  const [forecast, setForecast] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetch('/api/cities')
      .then((res) => res.json())
      .then(setCities);
  }, []);

  useEffect(() => {
    if (!selectedCity) {
      setForecast([]);
      return;
    }
    setLoading(true);
    fetch(`/api/forecast?city=${encodeURIComponent(selectedCity)}`)
      .then((res) => res.json())
      .then((data) => {
        setForecast(data);
        setLoading(false);
      });
  }, [selectedCity]);

  return (
    <div className="app">
      <h1>Weather Forecast</h1>

      <select
        value={selectedCity}
        onChange={(e) => setSelectedCity(e.target.value)}
      >
        <option value="">Select a city...</option>
        {cities.map((c) => (
          <option key={c.name} value={c.name}>
            {c.name}, {c.country}
          </option>
        ))}
      </select>

      {loading && <p className="loading">Loading forecast...</p>}

      {forecast.length > 0 && (
        <div className="forecast-grid">
          {forecast.map((day) => (
            <div key={day.date} className="forecast-card">
              <div className="date">{day.date}</div>
              <div className="emoji">
                {conditionEmoji[day.condition] || '🌡️'}
              </div>
              <div className="temp">{day.temperatureC}°C</div>
              <div className="condition">{day.condition}</div>
              <div className="details">
                <span>💧 {day.humidity}%</span>
                <span>💨 {day.windKph} km/h</span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default App;
