package com.example.mlbweatherforecast.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mlbweatherforecast.utilities.ForecastUtility

class ForecastViewModelFactory(
    private val forecastUtility: ForecastUtility
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)){
            return ForecastViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}