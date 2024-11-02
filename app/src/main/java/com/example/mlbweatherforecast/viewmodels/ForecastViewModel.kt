package com.example.mlbweatherforecast.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mlbweatherforecast.constants.TAG
import com.example.mlbweatherforecast.data.ForecastData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForecastViewModel : ViewModel() {

    private val _isOnline = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean> get() = _isOnline

    private val _forecastList = MutableStateFlow<List<ForecastData>>(emptyList())
    val forecastList: StateFlow<List<ForecastData>> get() = _forecastList


    fun fetchWeather(zipCode: String) {
        //todo: API logic
    }

    fun checkNetworkStatus(context: Context)
    {
        //todo: implement way to check internet connection
    }

    fun onDaySelected(forecast: ForecastData){
        Log.i(TAG, "forecast selected")
        //todo: implement going to next view
    }
}