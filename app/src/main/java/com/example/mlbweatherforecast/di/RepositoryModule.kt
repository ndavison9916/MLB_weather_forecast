package com.example.mlbweatherforecast.di

import com.example.mlbweatherforecast.data.repository.ForecastRepositoryImpl
import com.example.mlbweatherforecast.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindForecastRepository(
        forecastRepositoryImpl: ForecastRepositoryImpl
    ): ForecastRepository
}