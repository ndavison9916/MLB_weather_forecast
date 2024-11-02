package com.example.mlbweatherforecast.data

class SampleDataProvider {

    companion object{
        private val forecast1 = ForecastData("11/1/2024", 70.0, "mostly sunny", "5mph NW")
        private val forecast2 = ForecastData("11/2/2024", 72.0, "cloudy", "5mph SW")
        private val forecast3 = ForecastData("11/3/2024", 80.0, "partly cloudy", "10mph E")
        private val forecast4 = ForecastData("11/4/2024", 75.0, "sunny", "5mph N")
        private val forecast5 = ForecastData("11/5/2024", 60.0, "mostly sunny", "10mph NW")
    }


    fun getSampleForecast(): List<ForecastData> = arrayListOf(
        forecast1,
        forecast2,
        forecast3,
        forecast4,
        forecast5
    )

}