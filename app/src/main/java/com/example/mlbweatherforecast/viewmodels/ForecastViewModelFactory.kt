package com.example.mlbweatherforecast.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mlbweatherforecast.utilities.ForecastUtility

class ForecastViewModelFactory(
    private val forecastUtility: ForecastUtility,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)){
            return ForecastViewModel(app, forecastUtility) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}