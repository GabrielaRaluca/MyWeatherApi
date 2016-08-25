package com.example.gabriela.myweatherapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gabriela on 8/25/2016.
 */
public interface WeatherAPI {

    @GET("2.5/weather?units=metric&appid=794b1e9f8c72b3bd3ebee1d66de1c9dd")
    Call<WeatherInfo> loadQuestions(@Query("q") String cityName);
}
