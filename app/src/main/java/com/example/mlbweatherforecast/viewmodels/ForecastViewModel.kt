package com.example.mlbweatherforecast.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mlbweatherforecast.BuildConfig
import com.example.mlbweatherforecast.constants.ISO_COUNTRY_CODE_US
import com.example.mlbweatherforecast.constants.TAG
import com.example.mlbweatherforecast.data.AppDatabase
import com.example.mlbweatherforecast.data.ForecastEntity
import com.example.mlbweatherforecast.responses.GeoZipResponse
import com.example.mlbweatherforecast.responses.OneCallResponse
import com.example.mlbweatherforecast.utilities.DateFormatterUtility
import com.example.mlbweatherforecast.utilities.DirectionUtility
import com.example.mlbweatherforecast.utilities.ForecastUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastViewModel(app: Application, private val forecastUtility: ForecastUtility) : AndroidViewModel(app) {

    private val _isOnline = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean> get() = _isOnline

    private val _forecastList = MutableStateFlow<List<ForecastEntity>>(emptyList())
    val forecastList: StateFlow<List<ForecastEntity>> get() = _forecastList

    private val _location = MutableStateFlow("")

    private val database = AppDatabase.getInstance(app)

    init {
        _isOnline.value = checkNetworkStatus(app.applicationContext)
        fetchWeatherFromDatabase()
    }

    suspend fun fetchWeather(zipCode: String) {
        val internetCheckJob = viewModelScope.launch {
            try {
                _isOnline.value = checkNetworkStatus(getApplication())
            }
            catch (e: Exception)
            {
                Log.e(TAG, e.toString())
                _isOnline.value = false
            }
        }

        internetCheckJob.join()

        if (_isOnline.value == true)
        {
            val weatherFetchJob = viewModelScope.launch {
                try
                {
                    var zipQuery = zipCode + "," + ISO_COUNTRY_CODE_US

                    val geoZipResponse = forecastUtility.geoZipAPI.getLatLongByZip(zipQuery, BuildConfig.OPENWEATHER_API_KEY)

                    var coordinatesPair : Pair<Double, Double> = processGeoZipResponse(geoZipResponse)

                    val lat = coordinatesPair.first
                    val long = coordinatesPair.second

                    val oneCallResponse = forecastUtility.oneCallAPI.getOneCallForecast(lat, long, BuildConfig.OPENWEATHER_API_KEY)

                    _forecastList.value = processOneCallResponse(oneCallResponse)

                    deleteForecasts()

                }
                catch (e: Exception)
                {
                    Log.e(TAG, e.toString())
                    _isOnline.value = false
                }
            }

            weatherFetchJob.join()

            addForecasts(_forecastList.value)
        }
        else
        {
            fetchWeatherFromDatabase()
        }
    }

    fun checkNetworkStatus(context: Context) : Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    fun deleteForecasts()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                database?.forecastDao()?.deleteAllForecasts()
            }
        }
    }

    fun addForecasts(forecasts : List<ForecastEntity>){
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                database?.forecastDao()?.insertAll(forecasts)
            }
        }
    }


    fun fetchWeatherFromDatabase()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                _forecastList.value = database?.forecastDao()?.getAll()!!
            }
        }
    }


    fun processGeoZipResponse(response : GeoZipResponse): Pair<Double, Double>
    {
        Log.i(TAG, response.toString())
        _location.value = response.name
        return Pair(response.lat, response.lon)
    }

    fun processOneCallResponse(response : OneCallResponse): List<ForecastEntity>
    {
        Log.i(TAG, response.toString())
        var responseForecastData : MutableList<ForecastEntity> = mutableListOf()
        val dateFormatUtil = DateFormatterUtility()
        val directionUtil = DirectionUtility()

        response.daily.forEach{ dailyForecast ->
            val dateString = dateFormatUtil.convertUnixToReadableDate(dailyForecast.dt)
            val directionString = directionUtil.degreesToCompassDirection(dailyForecast.wind_deg)

            val dayForecastData = ForecastEntity(
                dateString,
                _location.value,
                dailyForecast.temp.min,
                dailyForecast.temp.max,
                dailyForecast.weather.first().description,
                dailyForecast.wind_speed,
                directionString,
                dailyForecast.summary,
                dailyForecast.pop
            )

            responseForecastData.add(dayForecastData)
        }

        return responseForecastData
    }
}