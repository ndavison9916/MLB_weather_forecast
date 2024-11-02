package com.example.mlbweatherforecast.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.data.SampleDataProvider
import com.example.mlbweatherforecast.viewmodels.ForecastViewModel


@Preview
@Composable
fun ForecastScreen(viewModel: ForecastViewModel)
{
    var zipCode by remember { mutableStateOf("") }

    val forecastList by viewModel.forecastList.collectAsState(initial = emptyList())

    val isOnline by viewModel.isOnline.collectAsState(initial = true)

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
                viewModel.fetchWeather(zipCode)
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

        LazyColumn {
            var sampleData = SampleDataProvider()
            var sampleForecastList = sampleData.getSampleForecast()

            items(sampleForecastList) { forecast ->
                ForecastItem(forecast = forecast, onClick = {viewModel.onDaySelected(forecast)})
            }
//            items(forecastList) { forecast ->
//                ForecastItem(forecast = forecast, onClick = {viewModel.onDaySelected(forecast)})
//            }
        }
    }
}