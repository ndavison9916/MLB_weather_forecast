package com.example.mlbweatherforecast.data

/**
 * provides sample ForecastEntities for unit and UI testing
 */
class SampleDataProvider {

    companion object{
        private val forecast1 = ForecastEntity("11/1/2024", "Denver", 60.0, 80.0, "mostly sunny", 5.0, "N", "mostly sunny with a small chance of sprinkles", 0.05)
        private val forecast2 = ForecastEntity("11/2/2024", "Denver", 59.0, 79.0, "cloudy", 10.0, "NNE", "cloudy with a high chance of rain", .50)
        private val forecast3 = ForecastEntity("11/3/2024", "Denver", 62.0, 85.0,"partly cloudy", 10.0, "SW", "partly cloudy with a small chance of rain", .1)
        private val forecast4 = ForecastEntity("11/4/2024", "Denver", 55.0, 78.0,"sunny", 15.0, "SW", "sunny day with higher winds and low chance of rain", .05)
        private val forecast5 = ForecastEntity("11/5/2024", "Denver", 60.0, 72.0, "mostly sunny", 7.0, "NW","mostly sunny with scattered showers in the afternoon", .45)
    }


    fun getSampleForecast(): List<ForecastEntity> = arrayListOf(
        forecast1,
        forecast2,
        forecast3,
        forecast4,
        forecast5
    )

}