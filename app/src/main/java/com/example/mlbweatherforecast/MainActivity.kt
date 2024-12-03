package com.example.mlbweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mlbweatherforecast.presentation.ui.views.DetailedDailyForecast
import com.example.mlbweatherforecast.presentation.ui.views.ForecastScreen
import com.example.mlbweatherforecast.presentation.ui.theme.MLBWeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MLBWeatherForecastTheme {
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        ForecastScreen(navController)
                    }

                    composable("detail/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toInt() ?: -1
                        DetailedDailyForecast(navController, index)
                    }
                }
            }
        }
    }
}
