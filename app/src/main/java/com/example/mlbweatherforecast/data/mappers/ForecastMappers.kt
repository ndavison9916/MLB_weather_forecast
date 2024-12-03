package com.example.mlbweatherforecast.data.mappers

import com.example.mlbweatherforecast.dao.ForecastEntity
import com.example.mlbweatherforecast.data.models.OneCallDataDto
import com.example.mlbweatherforecast.domain.utilities.DateFormatterUtility
import com.example.mlbweatherforecast.domain.utilities.DirectionUtility

/**
 * Function which maps the OneCallDataDto to a ForecastEntity
 */
fun OneCallDataDto.toDailyForecastList(location: String): List<ForecastEntity>
{
    val dateFormatUtil = DateFormatterUtility()
    val directionUtil = DirectionUtility()
    val responseForecastData : MutableList<ForecastEntity> = mutableListOf()

    daily.forEach{ dailyForecast ->

        val dateString = dateFormatUtil.convertUnixToReadableDate(dailyForecast.dt)
        val directionString = directionUtil.degreesToCompassDirection(dailyForecast.wind_deg)

        val dayForecastData = ForecastEntity(
            dateString,
            location,
            dailyForecast.temp.min,
            dailyForecast.temp.max,
            dailyForecast.weather.first().description,
            dailyForecast.wind_speed,
            directionString,
            dailyForecast.summary,
            dailyForecast.pop,
            dailyForecast.weather.first().icon
        )

        responseForecastData.add(dayForecastData)
    }

    return responseForecastData
}

