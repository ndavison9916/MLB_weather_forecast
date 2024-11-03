package com.example.mlbweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.mlbweatherforecast.ui.composables.ForecastScreen
import com.example.mlbweatherforecast.ui.theme.MLBWeatherForecastTheme
import com.example.mlbweatherforecast.viewmodels.ForecastViewModel

class MainActivity : ComponentActivity() {

    private val forecastViewModel: ForecastViewModel by viewModels()

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
