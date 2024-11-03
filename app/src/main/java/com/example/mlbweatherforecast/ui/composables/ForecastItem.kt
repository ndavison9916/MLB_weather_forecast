package com.example.mlbweatherforecast.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.data.ForecastData


@Composable
fun ForecastItem(forecast: ForecastData, onClick: () -> Unit)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(forecast.date)
            Text(stringResource(R.string.temperature) + "${forecast.maxTemp}" + stringResource(R.string.degrees_F))
            Text(stringResource(R.string.wind) + "${forecast.windSpeed} mph " + forecast.windDirection)
            Text(stringResource(R.string.description) + forecast.description)
        }
    }
}