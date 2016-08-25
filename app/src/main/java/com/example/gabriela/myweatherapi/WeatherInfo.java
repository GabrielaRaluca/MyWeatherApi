package com.example.gabriela.myweatherapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriela on 8/25/2016.
 */
public class WeatherInfo {

    private List<Weather> weather = new ArrayList<Weather>();
    private String name;
    private Main main;
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public String getName() {
        return name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }
}
