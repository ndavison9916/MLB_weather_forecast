package com.example.mlbweatherforecast.presentation.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.WeatherApp
import com.example.mlbweatherforecast.data.models.CurrentForecast
import com.example.mlbweatherforecast.domain.utilities.DirectionUtility
import com.example.mlbweatherforecast.presentation.utilities.IconUtility


@Composable
fun CurrentForecast(currentForecast: CurrentForecast) {
    val context = WeatherApp.instance.applicationContext
    val iconUtil = IconUtility()
    val directionUtility = DirectionUtility()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Current Weather",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp))
            Image(
                painter = painterResource(id = iconUtil.getIconDrawable(context, currentForecast.weather.first().icon)),
                contentDescription = "weather icon",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit,
            )

            Text(text = currentForecast.weather.first().description,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp))

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_temp),
                    contentDescription = "temperature",
                    modifier = Modifier
                        .size(40.dp)
                )
                Text(
                    text = "${currentForecast.temp}" + stringResource(R.string.degrees_F),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp))

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_wind),
                    contentDescription = "wind",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))

                Text(text = "${currentForecast.wind_speed} mph " + directionUtility.degreesToCompassDirection(currentForecast.wind_deg),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp))
            }
          }
    }

}