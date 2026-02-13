package com.example.weather.service;

import com.example.weather.model.City;
import com.example.weather.model.Forecast;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private static final List<City> CITIES = List.of(
            new City("New York", "USA"),
            new City("London", "UK"),
            new City("Tokyo", "Japan"),
            new City("Paris", "France"),
            new City("Sydney", "Australia"),
            new City("São Paulo", "Brazil"),
            new City("Dubai", "UAE"),
            new City("Toronto", "Canada"),
            new City("Berlin", "Germany"),
            new City("Mumbai", "India")
    );

    private static final Map<String, int[]> TEMPS = Map.of(
            "New York", new int[]{5, 7, 3, 6, 8},
            "London", new int[]{8, 9, 7, 10, 8},
            "Tokyo", new int[]{12, 14, 11, 13, 15},
            "Paris", new int[]{9, 11, 8, 10, 12},
            "Sydney", new int[]{25, 27, 24, 26, 28},
            "São Paulo", new int[]{28, 30, 27, 29, 31},
            "Dubai", new int[]{32, 34, 33, 35, 31},
            "Toronto", new int[]{-2, 0, -4, 1, -1},
            "Berlin", new int[]{4, 6, 3, 5, 7},
            "Mumbai", new int[]{30, 31, 29, 32, 30}
    );

    private static final Map<String, String[]> CONDITIONS = Map.of(
            "New York", new String[]{"Cloudy", "Rain", "Snow", "Cloudy", "Sunny"},
            "London", new String[]{"Rain", "Cloudy", "Rain", "Overcast", "Partly Cloudy"},
            "Tokyo", new String[]{"Sunny", "Partly Cloudy", "Sunny", "Rain", "Sunny"},
            "Paris", new String[]{"Cloudy", "Sunny", "Rain", "Partly Cloudy", "Sunny"},
            "Sydney", new String[]{"Sunny", "Sunny", "Partly Cloudy", "Sunny", "Thunderstorm"},
            "São Paulo", new String[]{"Thunderstorm", "Sunny", "Rain", "Partly Cloudy", "Sunny"},
            "Dubai", new String[]{"Sunny", "Sunny", "Sunny", "Haze", "Sunny"},
            "Toronto", new String[]{"Snow", "Cloudy", "Snow", "Partly Cloudy", "Snow"},
            "Berlin", new String[]{"Overcast", "Rain", "Cloudy", "Partly Cloudy", "Rain"},
            "Mumbai", new String[]{"Sunny", "Haze", "Partly Cloudy", "Sunny", "Haze"}
    );

    private static final Map<String, int[]> HUMIDITY = Map.of(
            "New York", new int[]{65, 80, 70, 60, 55},
            "London", new int[]{85, 80, 90, 82, 75},
            "Tokyo", new int[]{50, 55, 45, 70, 48},
            "Paris", new int[]{72, 60, 78, 65, 58},
            "Sydney", new int[]{40, 38, 50, 42, 55},
            "São Paulo", new int[]{75, 60, 80, 65, 55},
            "Dubai", new int[]{30, 28, 25, 35, 30},
            "Toronto", new int[]{70, 68, 75, 62, 72},
            "Berlin", new int[]{78, 82, 76, 68, 80},
            "Mumbai", new int[]{55, 60, 58, 50, 62}
    );

    private static final Map<String, int[]> WIND = Map.of(
            "New York", new int[]{20, 25, 15, 18, 12},
            "London", new int[]{30, 25, 35, 28, 20},
            "Tokyo", new int[]{10, 15, 8, 20, 12},
            "Paris", new int[]{18, 12, 22, 15, 10},
            "Sydney", new int[]{15, 12, 20, 14, 25},
            "São Paulo", new int[]{10, 8, 15, 12, 8},
            "Dubai", new int[]{22, 18, 20, 15, 25},
            "Toronto", new int[]{28, 22, 30, 18, 25},
            "Berlin", new int[]{20, 25, 18, 15, 22},
            "Mumbai", new int[]{12, 10, 15, 8, 14}
    );

    public List<City> getCities() {
        return CITIES;
    }

    public List<Forecast> getForecast(String cityName) {
        if (!TEMPS.containsKey(cityName)) {
            return List.of();
        }

        LocalDate today = LocalDate.now();
        int[] temps = TEMPS.get(cityName);
        String[] conds = CONDITIONS.get(cityName);
        int[] hum = HUMIDITY.get(cityName);
        int[] wind = WIND.get(cityName);

        return List.of(
                new Forecast(cityName, today.toString(), temps[0], conds[0], hum[0], wind[0]),
                new Forecast(cityName, today.plusDays(1).toString(), temps[1], conds[1], hum[1], wind[1]),
                new Forecast(cityName, today.plusDays(2).toString(), temps[2], conds[2], hum[2], wind[2]),
                new Forecast(cityName, today.plusDays(3).toString(), temps[3], conds[3], hum[3], wind[3]),
                new Forecast(cityName, today.plusDays(4).toString(), temps[4], conds[4], hum[4], wind[4])
        );
    }
}
