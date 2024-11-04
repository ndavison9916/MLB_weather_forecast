package com.example.mlbweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mlbweatherforecast.services.ForecastAPIService
import com.example.mlbweatherforecast.services.GeoZipAPIService
import com.example.mlbweatherforecast.ui.composables.DetailedDailyForecast
import com.example.mlbweatherforecast.ui.composables.ForecastScreen
import com.example.mlbweatherforecast.ui.theme.MLBWeatherForecastTheme
import com.example.mlbweatherforecast.utilities.ForecastUtility
import com.example.mlbweatherforecast.viewmodels.ForecastViewModel
import com.example.mlbweatherforecast.viewmodels.ForecastViewModelFactory

class MainActivity : ComponentActivity() {

    //create view model with dependency injection / factory method
    private val forecastViewModel: ForecastViewModel by viewModels()
    {
        ForecastViewModelFactory(ForecastUtility(
            ForecastAPIService.create(),
            GeoZipAPIService.create()
        ), this.application)
    }

    /**
     * sets up the main UI entry for the application
     * defines navigation graph
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MLBWeatherForecastTheme {
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        ForecastScreen(navController, forecastViewModel)
                    }

                    composable("detail/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toInt() ?: -1
                        DetailedDailyForecast(navController, forecastViewModel, index)
                    }
                }
            }
        }
    }
}
