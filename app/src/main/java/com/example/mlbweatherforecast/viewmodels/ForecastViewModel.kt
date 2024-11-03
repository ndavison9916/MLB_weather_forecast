package com.example.mlbweatherforecast.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mlbweatherforecast.BuildConfig
import com.example.mlbweatherforecast.constants.TAG
import com.example.mlbweatherforecast.data.ForecastData
import com.example.mlbweatherforecast.responses.DailyForecast
import com.example.mlbweatherforecast.responses.GeoZipResponse
import com.example.mlbweatherforecast.responses.OneCallResponse
import com.example.mlbweatherforecast.services.GeoZipAPIService
import com.example.mlbweatherforecast.utilities.ForecastUtility
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(private val forecastUtility: ForecastUtility) : ViewModel() {

    private val _isOnline = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean> get() = _isOnline

    private val _forecastList = MutableStateFlow<List<ForecastData>>(emptyList())
    val forecastList: StateFlow<List<ForecastData>> get() = _forecastList

    private val _cityName = MutableStateFlow("")
    val cityName : StateFlow<String> get() = _cityName

    init {

    }


    fun fetchWeather(zipCode: String) {
        viewModelScope.launch {
            try
            {
                val geoZipResponse = forecastUtility.geoZipAPI.getLatLongByZip(zipCode, BuildConfig.OPENWEATHER_API_KEY)

                var coordinatesPair : Pair<Double, Double> = processGeoZipResponse(geoZipResponse)

                val lat = coordinatesPair.first
                val long = coordinatesPair.second

                val oneCallResponse = forecastUtility.oneCallAPI.getOneCallForecast(lat, long, BuildConfig.OPENWEATHER_API_KEY)

                _forecastList.value = processOneCallResponse(oneCallResponse)
            }
            catch (e: Exception)
            {
                Log.e(TAG, e.toString())
            }
        }
    }

    fun checkNetworkStatus(context: Context)
    {
        //todo: implement way to check internet connection
    }

    fun onDaySelected(forecast: ForecastData){
        Log.i(TAG, "forecast selected")
        //todo: implement going to next view
    }

    fun processGeoZipResponse(response : GeoZipResponse): Pair<Double, Double>
    {
        _cityName.value = response.name
        return Pair(response.lat, response.lon)
    }

    fun processOneCallResponse(response : OneCallResponse): List<ForecastData>
    {
        var responseForecastData : MutableList<ForecastData> = mutableListOf()

        response.daily.forEach{ dailyForecast ->
            val dayForecastData = ForecastData(
                dailyForecast.dt.toString(),
                dailyForecast.temp.min,
                dailyForecast.temp.max,
                dailyForecast.weather.first().description,
                dailyForecast.wind_speed,
                dailyForecast.wind_deg
            )

            responseForecastData.add(dayForecastData)
        }

        return responseForecastData
    }
}