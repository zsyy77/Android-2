package com.example.weatherforecast;

public interface WeatherDataCallback {
    void onSuccess(WeatherData weatherData);
    void onError(String errorMessage);
}