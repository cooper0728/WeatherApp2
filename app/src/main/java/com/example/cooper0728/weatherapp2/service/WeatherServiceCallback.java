package com.example.cooper0728.weatherapp2.service;

import com.example.cooper0728.weatherapp2.data.Channel;

/**
 * Created by cooper0728 on 10/15/2017.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception expection);

}
