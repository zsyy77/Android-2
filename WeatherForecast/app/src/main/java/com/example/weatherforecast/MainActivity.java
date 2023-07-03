package com.example.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "WeatherPrefs";
    private static final String KEY_CITY_ID = "cityId";
    private static final String KEY_PROVINCE = "province";
    private static final String KEY_CITY = "city";
    private static final String KEY_UPDATE_TIME = "updateTime";
    private static final String KEY_TEMPERATURE = "temperature";
    private static final String KEY_HUMIDITY = "humidity";
    private EditText etCityId;
    private Button btnGetWeather;
    private TextView tvWeatherInfo;

    private WeatherApiClient weatherApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        etCityId = findViewById(R.id.et_city_id);
        btnGetWeather = findViewById(R.id.btn_get_weather);
        tvWeatherInfo = findViewById(R.id.tv_weather_info);

        weatherApiClient = new WeatherApiClient(this);

        btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityId = etCityId.getText().toString();
                getWeatherData(cityId);
            }
        });
    }

    private void getWeatherData(String cityId) {
        weatherApiClient.getWeatherData(cityId, new WeatherDataCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
                String weatherInfo = "省份: " + weatherData.getProvince() + "\n"
                        + "城市: " + weatherData.getCity() + "\n"
                        + "更新时间: " + weatherData.getUpdateTime() + "\n"
                        + "温度: " + weatherData.getTemperature() + "\n"
                        + "湿度: " + weatherData.getHumidity();

                tvWeatherInfo.setText(weatherInfo);
            }

            @Override
            public void onError(String errorMessage) {
                tvWeatherInfo.setText("获取天气数据失败：" + errorMessage);
            }
        });
    }
}