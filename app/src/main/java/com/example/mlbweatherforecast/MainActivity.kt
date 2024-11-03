package com.example.mlbweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.mlbweatherforecast.services.ForecastAPIService
import com.example.mlbweatherforecast.services.GeoZipAPIService
import com.example.mlbweatherforecast.services.WeatherOneCallAPI
import com.example.mlbweatherforecast.ui.composables.ForecastScreen
import com.example.mlbweatherforecast.ui.theme.MLBWeatherForecastTheme
import com.example.mlbweatherforecast.utilities.ForecastUtility
import com.example.mlbweatherforecast.viewmodels.ForecastViewModel
import com.example.mlbweatherforecast.viewmodels.ForecastViewModelFactory

class MainActivity : ComponentActivity() {

    private val forecastViewModel: ForecastViewModel by viewModels()
    {
        ForecastViewModelFactory(ForecastUtility(
            ForecastAPIService.create(),
            GeoZipAPIService.create()
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MLBWeatherForecastTheme {
                Surface(){
                    ForecastScreen(forecastViewModel)
                }
            }
        }
    }
}
