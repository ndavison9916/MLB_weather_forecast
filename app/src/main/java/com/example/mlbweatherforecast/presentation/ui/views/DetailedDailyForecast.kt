package com.example.mlbweatherforecast.presentation.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.WeatherApp
import com.example.mlbweatherforecast.presentation.viewmodels.ForecastViewModel
import com.example.mlbweatherforecast.utilities.IconUtility

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDailyForecast(navController: NavController, viewModel: ForecastViewModel, index: Int) {
    val forecasts by viewModel.forecastList.collectAsState(initial = emptyList())

    val forecast = forecasts.getOrNull(index)

    val context = WeatherApp.instance.applicationContext

    val iconUtil = IconUtility()

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
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                )
                {
                    Column(
                        modifier = Modifier.padding(paddingValues)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(forecast.location,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp))
                        Text(forecast.date,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp))
                        Image(
                            painter = painterResource(id = iconUtil.getIconDrawable(context, forecast.icon)),
                            contentDescription = "Example Vector Image",
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Fit,
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            Text(
                                stringResource(R.string.low) + "${forecast.minTemp}" + stringResource(R.string.degrees_F)
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                stringResource(R.string.high) + "${forecast.maxTemp}" + stringResource(R.string.degrees_F)
                            )

                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_wind),
                                contentDescription = "wind",
                                modifier = Modifier
                                    .size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text("${forecast.windSpeed} mph " + forecast.windDirection)
                            Spacer(modifier = Modifier.width(12.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_rain_snow),
                                contentDescription = "precipitation",
                                modifier = Modifier
                                    .size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text("${forecast.pop * 100} %")
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                        ) {
                            Text(stringResource(R.string.description) + forecast.summary)
                        }
                    }
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