package com.example.weatherforecast;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherApiClient {
    private static final String TAG = WeatherApiClient.class.getSimpleName();

    private static final String BASE_URL = "http://api.map.baidu.com/location/ip?ak=pXbQRnMzrXDOr33U2h3WVFaxN4NeM9QQ&ip=115.45.169.246&coor=bd09ll"; // 替换成实际的 API 地址

    private RequestQueue requestQueue;

    public WeatherApiClient(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getWeatherData(String cityId, final WeatherDataCallback callback) {
        String apiUrl = BASE_URL + "/api/weather?cityId=" + cityId;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("data")) {
                                JSONObject dataObject = response.getJSONObject("data");
                                String province = dataObject.getString("province");
                                String city = dataObject.getString("city");
                                String updateTime = dataObject.getString("updateTime");
                                String temperature = dataObject.getString("temperature");
                                String humidity = dataObject.getString("humidity");

                                WeatherData weatherData = new WeatherData(province, city, updateTime, temperature, humidity);
                                callback.onSuccess(weatherData);
                            } else {
                                callback.onError("获取天气数据为空");
                            }
                        } catch (JSONException e) {
                            Log.d(TAG, "JSON 解析错误: " + e.getMessage());
                            callback.onError("JSON 解析错误");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "网络请求失败: " + error.getMessage());
                        callback.onError("网络请求失败");
                    }
                });

        requestQueue.add(request);
    }
}