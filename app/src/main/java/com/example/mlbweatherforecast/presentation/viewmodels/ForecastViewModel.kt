package com.example.mlbweatherforecast.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mlbweatherforecast.dao.ForecastEntity
import com.example.mlbweatherforecast.data.mappers.toDailyForecastList
import com.example.mlbweatherforecast.data.models.OneCallDataDto
import com.example.mlbweatherforecast.domain.repository.CachedForecastRepository
import com.example.mlbweatherforecast.domain.repository.ForecastRepository
import com.example.mlbweatherforecast.domain.utilities.NetworkChecker
import com.example.mlbweatherforecast.domain.utilities.Resource
import com.example.mlbweatherforecast.presentation.states.ForecastState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor (
    private val repository: ForecastRepository,
    private val cachedForecastRepository: CachedForecastRepository,
    private val networkChecker: NetworkChecker,
): ViewModel(){
    var state by mutableStateOf(ForecastState())

    private val _networkStatus = MutableStateFlow<Boolean>(false)
    val networkStatus: StateFlow<Boolean> = _networkStatus.asStateFlow()

    private val _forecastList = MutableStateFlow<List<ForecastEntity>>(emptyList())
    val forecastList: StateFlow<List<ForecastEntity>> get() = _forecastList

    init
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                checkNetworkStatus()
                fetchWeatherFromDatabase()
            }
        }

    }

    fun checkNetworkStatus()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                _networkStatus.value = networkChecker.isConnected()
            }
        }
    }

    fun loadForecastInfo(zip: String)
    {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            checkNetworkStatus()

            if (_networkStatus.value) {

                when (val zipResult = repository.getLatLongByZip(zip)) {
                    //fetch location success, now fetch forecast
                    is Resource.Success -> {

                        if (zipResult.data != null) {
                            val lat = zipResult.data.lat
                            val lon = zipResult.data.lon
                            val location = zipResult.data.name

                            when (val forecastResult = repository.getOneCallForecast(lat, lon)) {
                                is Resource.Success -> {
                                    if (forecastResult.data != null) {
                                        processOneCallData(forecastResult.data, location)
                                    } else {
                                        handleError("forecast data is null")
                                    }
                                }

                                is Resource.Error -> {
                                    handleError("error fetching forecast data")
                                }
                            }
                        } else {
                            handleError("zip code is null")
                        }
                    }

                    // fetching location failed
                    is Resource.Error -> {
                        handleError("error fetching location")
                    }
                }
            }
            else
            {
                viewModelScope.launch {
                    withContext(Dispatchers.IO)
                    {
                        val forecasts = cachedForecastRepository.fetchWeatherFromDatabase()
                        if (forecasts != null) {
                            _forecastList.value = forecasts
                        }
                    }
                }
            }
        }
    }

    fun handleError(message : String){
        state = state.copy(
            isLoading = false,
            error = message
        )
    }

    fun processOneCallData(oneCallData: OneCallDataDto, location: String)
    {
        val forecasts = oneCallData.toDailyForecastList(location)

        _forecastList.value = forecasts

        val currentForecast = oneCallData.current

        deleteForecasts()

        addForecasts(forecasts)


        state = state.copy(
            currentForecast = currentForecast,
            isLoading = false,
            error = null
        )
    }

    /**
     * deletes the existing forecast in the cached database
     */
    fun deleteForecasts()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                cachedForecastRepository.deleteForecasts()
            }
        }
    }

    /**
     * adds the forecast list to the cached database
     */
    fun addForecasts(forecasts : List<ForecastEntity>){
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                cachedForecastRepository.addForecasts(forecasts)
            }
        }
    }

    /**
     * retrieves the cached forecasts from the database
     */
    fun fetchWeatherFromDatabase()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val forecasts = cachedForecastRepository.fetchWeatherFromDatabase()
                if (forecasts != null) {
                    _forecastList.value = forecasts
                }
            }
        }
    }

}