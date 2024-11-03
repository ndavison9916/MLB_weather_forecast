package com.example.mlbweatherforecast.data

class SampleDataProvider {

    companion object{
        private val forecast1 = ForecastData("11/1/2024", 60.0, 80.0, "mostly sunny", 5.0, "N")
        private val forecast2 = ForecastData("11/2/2024", 59.0, 79.0, "cloudy", 10.0, "NNE")
        private val forecast3 = ForecastData("11/3/2024", 62.0, 85.0,"partly cloudy", 10.0, "SW")
        private val forecast4 = ForecastData("11/4/2024", 55.0, 78.0,"sunny", 15.0, "SW")
        private val forecast5 = ForecastData("11/5/2024", 60.0, 72.0, "mostly sunny", 7.0, "NW")
    }


    fun getSampleForecast(): List<ForecastData> = arrayListOf(
        forecast1,
        forecast2,
        forecast3,
        forecast4,
        forecast5
    )

}