package com.example.mlbweatherforecast.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(forecasts: List<ForecastEntity>)

    @Query("SELECT * FROM forecasts")
    fun getAll(): List<ForecastEntity>

    @Query("SELECT * FROM forecasts WHERE date = :date")
    fun getForecastById(date: String): ForecastEntity?

    @Query("SELECT COUNT(*) from forecasts")
    fun getCount(): Int

    @Query("DELETE FROM forecasts")
    fun deleteAllForecasts(): Int
}