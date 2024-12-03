package com.example.mlbweatherforecast

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp: Application()
{
    companion object {
        lateinit var instance: WeatherApp
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}