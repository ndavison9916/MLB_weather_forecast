package com.example.mlbweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mlbweatherforecast.data.remote.GeoZipAPI
import com.example.mlbweatherforecast.presentation.ui.views.DetailedDailyForecast
import com.example.mlbweatherforecast.presentation.ui.views.ForecastScreen
import com.example.mlbweatherforecast.presentation.ui.theme.MLBWeatherForecastTheme
import com.example.mlbweatherforecast.presentation.viewmodels.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val forecastViewModel: ForecastViewModel by viewModels()

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


    //create view model with dependency injection / factory method
//    private val forecastViewModelOld: ForecastViewModelOld by viewModels()
//    {
//        ForecastViewModelFactory(ForecastUtility(
//            ForecastAPI.create(),
//            GeoZipAPI.create()
//        ), this.application)
//    }
//
//    /**
//     * sets up the main UI entry for the application
//     * defines navigation graph
//     */
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        enableEdgeToEdge()
//        setContent {
//            val navController = rememberNavController()
//            MLBWeatherForecastTheme {
//                NavHost(navController = navController, startDestination = "main") {
//                    composable("main") {
//                        ForecastScreen(navController, forecastViewModelOld)
//                    }
//
//                    composable("detail/{index}") { backStackEntry ->
//                        val index = backStackEntry.arguments?.getString("index")?.toInt() ?: -1
//                        DetailedDailyForecast(navController, forecastViewModelOld, index)
//                    }
//                }
//            }
//        }
//    }
}
