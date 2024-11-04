package com.example.mlbweatherforecast.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.viewmodels.ForecastViewModel
import kotlinx.coroutines.launch


@Preview
@Composable
fun ForecastScreen(navHostController: NavHostController, viewModel: ForecastViewModel)
{
    var zipCode by remember { mutableStateOf("") }

    val forecastList by viewModel.forecastList.collectAsState(initial = emptyList())

    val isOnline by viewModel.isOnline.collectAsState(initial = true)

    val coroutineScope = rememberCoroutineScope()


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = zipCode,
            onValueChange = {
                zipCode = it
            },
            label = {Text(stringResource(R.string.enter_zip_code))},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.fetchWeather(zipCode)
                }
            }
        ) {
            Text(stringResource(R.string.get_weather))
        }

        Text(
            stringResource(
                R.string.network_status,
                if (isOnline) stringResource(R.string.online)
                else stringResource(R.string.offline)
            )
        )

        Text(
            if (forecastList.isNullOrEmpty() || forecastList.first().location == "") ""
            else stringResource(R.string.location) + forecastList.first().location
        )

        LazyColumn {
            //test UI with Sample Data
//            var sampleData = SampleDataProvider()
//            var sampleForecastList = sampleData.getSampleForecast()
//
//            items(sampleForecastList) { forecast ->
//                ForecastItem(forecast = forecast, onClick = {
//                    viewModel.onDaySelected(forecast)
//                    navHostController.navigate("detail/{1}")
//                })
//            }

            if (!isOnline)
            {
                coroutineScope.launch {
                    viewModel.fetchWeatherFromDatabase()
                }
            }

            itemsIndexed(forecastList) { index, forecast ->
                ForecastItem(forecast = forecast, onClick = {
                    navHostController.navigate("detail/$index")
                })
            }
        }
    }
}