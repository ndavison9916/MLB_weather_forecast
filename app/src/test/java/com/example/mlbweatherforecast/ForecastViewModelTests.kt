package com.example.mlbweatherforecast

import android.app.Application
import android.content.Context
import com.example.mlbweatherforecast.dao.AppDatabase
import com.example.mlbweatherforecast.dao.ForecastEntity
import com.example.mlbweatherforecast.data.models.CurrentForecast
import com.example.mlbweatherforecast.data.models.DailyForecast
import com.example.mlbweatherforecast.data.models.GeoZipDataDto
import com.example.mlbweatherforecast.data.models.OneCallDataDto
import com.example.mlbweatherforecast.data.models.Temperature
import com.example.mlbweatherforecast.data.models.WeatherDescription
import com.example.mlbweatherforecast.data.remote.GeoZipAPI
import com.example.mlbweatherforecast.presentation.viewmodels.ForecastViewModel
import org.junit.Before
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ForecastViewModelTests {

    private lateinit var forecastViewModel : ForecastViewModel
    private lateinit var mockAppDatabase : AppDatabase
    private lateinit var mockAppContext : Context

    private lateinit var mockApp : Application
    //private val mockOneCallApi = mockk<WeatherOneCallAPI>()
    private val mockGeoApi = mockk<GeoZipAPI>()



    @Before
    fun setUp(){
        mockAppDatabase = mockk(relaxed = true)
        mockAppContext = mockk(relaxed = true)
        mockApp = mockk(relaxed = true)

        mockkStatic(AppDatabase::class)
        every {(mockApp).applicationContext} returns mockAppContext
        every { AppDatabase.getInstance(mockAppContext) } returns mockAppDatabase

//        mockApi = ForecastUtility(mockOneCallApi, mockGeoApi)
//        forecastViewModel = spyk(ForecastViewModel(mockApp, mockApi))
//
//        every { forecastViewModel.checkNetworkStatus(any())} returns true

    }

    @Test
    fun `test fetchWeather returns valid data`() = runTest {
        //arrange
        val geoZipDataDto = GeoZipDataDto(39.7392,104.9903, "Denver")
        var weatherDescriptionList : MutableList<WeatherDescription> = mutableListOf()
        //val weatherDescription = WeatherDescription("test", "test")
        //weatherDescriptionList.add(weatherDescription)
        var dailyForecastList : MutableList<DailyForecast> = mutableListOf()
        val temp = Temperature(0.0,0.0,0.0,0.0,0.0,0.0)
        val dailyForecast = DailyForecast(1730739219, temp, weatherDescriptionList, 0, 0.0, 299.0, "", 0.0)
        dailyForecastList.add(dailyForecast)

        val oneCallDataDto = OneCallDataDto(CurrentForecast(0.0, 0, weatherDescriptionList, 0.0, 0.0), dailyForecastList)

        //val forecastEntity = ForecastEntity("Monday, November 4, 2024", "Denver", 0.0, 0.0, "", 0.0, "WNW", "", 0.0)

        val forecasts : MutableList<ForecastEntity> = mutableListOf()

        //forecasts.add(forecastEntity)

//        coEvery { mockApi.geoZipAPI.getLatLongByZip(any(), any()) } returns geoZipDataDto
//
//        coEvery { mockApi.oneCallAPI.getOneCallForecast(geoZipDataDto.lat, geoZipDataDto.lon, any()) } returns oneCallDataDto


        // Call the method to fetch weather
        //forecastViewModel.fetchWeather("80214")

        // Assert that the ViewModel's state matches the mocked data
        //assertEquals(forecasts, forecastViewModel.forecastList.value)
    }




}