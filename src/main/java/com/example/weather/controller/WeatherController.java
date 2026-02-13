package com.example.weather.controller;

import com.example.weather.model.City;
import com.example.weather.model.Forecast;
import com.example.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return weatherService.getCities();
    }

    @GetMapping("/forecast")
    public List<Forecast> getForecast(@RequestParam String city) {
        return weatherService.getForecast(city);
    }
}
