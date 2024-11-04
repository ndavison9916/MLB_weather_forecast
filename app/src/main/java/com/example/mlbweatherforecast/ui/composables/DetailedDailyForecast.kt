package com.example.mlbweatherforecast.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.viewmodels.ForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDailyForecast(navController: NavController, forecastViewModel: ForecastViewModel, index: Int) {
    val forecasts by forecastViewModel.forecastList.collectAsState(initial = emptyList())

    val forecast = forecasts.getOrNull(index)

    forecast?.let {
        Scaffold(
            modifier = Modifier.padding(16.dp),
            topBar = {
                TopAppBar(
                    title = { Text("Weather Details") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Text(forecast.location)
                    Text(forecast.date)
                    Text(
                        stringResource(R.string.low) + "${forecast.minTemp}" + stringResource(R.string.degrees_F)
                    )
                    Text(
                        stringResource(R.string.high) + "${forecast.maxTemp}" + stringResource(R.string.degrees_F)
                    )
                    Text(stringResource(R.string.wind) + "${forecast.windSpeed} mph " + forecast.windDirection)
                    Text(stringResource(R.string.chance_precipitation) + "${forecast.pop * 100} %")
                    Text(stringResource(R.string.description) + forecast.summary)
                }
            }
        )
    } ?:
    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            TopAppBar(
                title = { Text("Weather Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Text("No data available")
            }
        }
    )

}