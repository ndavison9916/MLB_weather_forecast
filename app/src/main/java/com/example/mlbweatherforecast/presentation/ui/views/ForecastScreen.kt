package com.example.mlbweatherforecast.presentation.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mlbweatherforecast.R
import com.example.mlbweatherforecast.presentation.viewmodels.ForecastViewModel
import kotlinx.coroutines.launch


@Composable
fun ForecastScreen(navHostController: NavHostController, viewModel: ForecastViewModel)
{
    var zipCode by remember { mutableStateOf("") }

    val currentForecast = viewModel.state.value.currentForecast

    val forecastList = viewModel.state.value.forecasts

    val isOnline = viewModel.state.value.isOnline

    val coroutineScope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current

    val keyboardController = LocalSoftwareKeyboardController.current


    Column(modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = zipCode,
            onValueChange = {
                zipCode = it
            },
            label = { Text(stringResource(R.string.enter_zip_code)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .clickable{
                    focusManager.clearFocus()
                },
            onClick = {
                coroutineScope.launch {
                    keyboardController?.hide()
                    viewModel.loadForecastInfo(zipCode)
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
            else stringResource(R.string.location) + forecastList.first().location,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
        )

        if (currentForecast != null) {
            CurrentForecast(currentForecast)
        }

        if (forecastList.size != 0)
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "calendar",
                    modifier = Modifier
                        .size(40.dp)
                )
                Text(
                    text = "7-Day Forecast",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            if (forecastList.size != 0)
            {
                itemsIndexed(forecastList) { index, forecast ->
                    ForecastItem(forecast = forecast, onClick = {
                        navHostController.navigate("detail/$index")
                    })
                }
            }
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
        }
    }
}