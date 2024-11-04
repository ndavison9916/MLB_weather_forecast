package com.example.mlbweatherforecast.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mlbweatherforecast.utilities.ForecastUtility

/**
 * Factory class for the ForecastViewModel class
 * Takes the forecastUtility which has references to the required APIs,
 * and a reference to the Application
 */
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