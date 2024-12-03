package com.example.mlbweatherforecast.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecasts")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = false)
    var date: String,
    var location: String,
    var minTemp: Double,
    var maxTemp: Double,
    var description: String,
    var windSpeed: Double,
    var windDirection: String,
    var summary: String,
    var pop: Double,
    var icon: String
){
    constructor() : this("", "",0.0, 0.0,
        "", 0.0, "", "", 0.0, "")
}


