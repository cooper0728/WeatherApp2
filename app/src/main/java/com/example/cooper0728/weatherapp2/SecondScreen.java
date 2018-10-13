package com.example.cooper0728.weatherapp2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooper0728.weatherapp2.data.Channel;
import com.example.cooper0728.weatherapp2.data.Item;
import com.example.cooper0728.weatherapp2.service.WeatherServiceCallback;
import com.example.cooper0728.weatherapp2.service.YahooWeatherService;

/**
 * Created by cooper0728 on 11/18/2017.
 */

public class SecondScreen extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private static final String TAG ="SecondScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_weather);
        Log.d(TAG, "onCreate: Starting.");

        //button to Navigate to FirstScreen
        Button btnNavToFirst = (Button) findViewById(R.id.btnGoToFirstScreen);

        //what happeens after you click on the FirstScreen button
        btnNavToFirst.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondScreen.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

       //weather cond
        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView =(TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Charlotte, NC");
    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();

        int resourceId = getResources().getIdentifier("drawable/icon_"+ item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
