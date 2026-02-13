package com.example.weather.model;

public record Forecast(String city, String date, int temperatureC, String condition, int humidity, int windKph) {
}
