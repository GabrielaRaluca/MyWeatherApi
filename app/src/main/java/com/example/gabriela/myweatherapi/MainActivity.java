package com.example.gabriela.myweatherapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gabriela on 8/25/2016.
 */
public class MainActivity extends Activity implements Callback<WeatherInfo> {


    Button getWeather;
    EditText cityNameEditText;
    ImageView icon;
    TextView cityName;
    TextView temp;
    TextView tempMax;
    TextView tempMin;
    TextView humidity;
    TextView windSpeed;
    TextView description;
    WeatherInfo weatherInfo;


    final String degreeSymbol = "\u00b0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void onClick(View view)
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        WeatherAPI stackOverflowAPI = retrofit.create(WeatherAPI.class);

        String cityNameString = cityNameEditText.getText().toString();

        Call<WeatherInfo> call = stackOverflowAPI.loadQuestions(cityNameString);
        //asynchronous call
        call.enqueue(this);

    }


    @Override
    public void onFailure(Call<WeatherInfo> call, Throwable t) {
        Toast.makeText(this, "Couldnt get info", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
        weatherInfo = response.body();

        int id = getResources().getIdentifier("com.example.gabriela.myweatherapi:drawable/" + "w" + weatherInfo.getWeather().get(0).getIcon(), null, null);
        icon.setImageResource(id);
        Log.v("Icon","drawable/" + "w" + weatherInfo.getWeather().get(0).getIcon() );
        cityName.setText("City Name: " + weatherInfo.getName());
        description.setText("Description: " + weatherInfo.getWeather().get(0).getDescription());
        temp.setText("Temperature: " + Double.toString(weatherInfo.getMain().getTemp())+ " " + degreeSymbol + "C");
        tempMax.setText("Temp. Max: " + Double.toString(weatherInfo.getMain().getTemp_max())+ " " + degreeSymbol + "C");
        tempMin.setText("Temp Min: " + Double.toString(weatherInfo.getMain().getTemp_min()) + " " + degreeSymbol + "C");
        humidity.setText("Humidity: " + Double.toString(weatherInfo.getMain().getHumidity()));
        windSpeed.setText("Wind Speed: " + Double.toString(weatherInfo.getWind().getSpeed()));
    }

    public void init()
    {
        cityNameEditText = (EditText) findViewById(R.id.cityname_edittext);
        getWeather = (Button) findViewById(R.id.getweather_button);
        icon = (ImageView) findViewById(R.id.imageView);
        cityName = (TextView) findViewById(R.id.cityname_textview);
        temp = (TextView) findViewById(R.id.temp_textview);
        tempMax = (TextView) findViewById(R.id.temp_max_textview);
        tempMin = (TextView) findViewById(R.id.temp_min_textview);
        humidity = (TextView) findViewById(R.id.humidity_textview);
        windSpeed = (TextView) findViewById(R.id.wind_speed_textview);
        description = (TextView) findViewById(R.id.description_textview);
    }
}
