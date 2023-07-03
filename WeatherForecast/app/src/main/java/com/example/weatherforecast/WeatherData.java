package com.example.weatherforecast;
import com.example.weatherforecast.WeatherApiClient;

public class WeatherData {
    private String province;
    private String city;
    private String updateTime;
    private String temperature;
    private String humidity;

    public WeatherData(String province, String city, String updateTime, String temperature, String humidity) {
        this.province = province;
        this.city = city;
        this.updateTime = updateTime;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }
}