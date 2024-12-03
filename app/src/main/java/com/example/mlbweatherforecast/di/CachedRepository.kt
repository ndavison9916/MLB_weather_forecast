package com.example.mlbweatherforecast.di

import com.example.mlbweatherforecast.data.repository.CachedForecastRepositoryImpl
import com.example.mlbweatherforecast.domain.repository.CachedForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class CachedRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCachedRepository(
        cachedForecastRepositoryImpl: CachedForecastRepositoryImpl
    ): CachedForecastRepository
}