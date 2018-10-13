package com.example.cooper0728.weatherapp2.data;

import org.json.JSONObject;

/**
 * Created by cooper0728 on 10/15/2017.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");

    }
}
