package com.example.mlbweatherforecast.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ForecastEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun forecastDao(): ForecastDao?

    companion object{
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns a Singleton Instance of the AppDatabase named forecasts.db on the device
         */
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "forecasts.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}