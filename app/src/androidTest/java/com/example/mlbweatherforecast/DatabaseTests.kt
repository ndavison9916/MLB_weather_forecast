package com.example.mlbweatherforecast

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mlbweatherforecast.dao.AppDatabase
import com.example.mlbweatherforecast.dao.ForecastDao
import com.example.mlbweatherforecast.dao.SampleDataProvider
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTests {

    private lateinit var dao: ForecastDao
    private lateinit var appDatabase: AppDatabase


    @Before
    fun createDb()
    {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        appDatabase = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = appDatabase.forecastDao()!!
    }

    @After
    fun closeDb()
    {
        appDatabase.close()
    }


    @Test
    fun testInsertAll()
    {
        //arrange
        val sampleDataProvider = SampleDataProvider()
        val forecasts = sampleDataProvider.getSampleForecast()

        //act
        dao.insertAll(forecasts)
        val count = dao.getCount()

        //assert
        assertEquals(count, forecasts.size)
    }

    @Test
    fun testGetAll()
    {
        //arrange
        val sampleDataProvider = SampleDataProvider()
        val forecasts = sampleDataProvider.getSampleForecast()
        dao.insertAll(forecasts)

        //act
        val listFromDb = dao.getAll()
        val count = listFromDb.size

        //assert
        assertEquals(count, forecasts.size)
    }


}