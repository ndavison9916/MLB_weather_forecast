package com.example.mlbweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        installSplashScreen()
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
