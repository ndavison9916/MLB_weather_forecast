package com.example.mlbweatherforecast.di

import android.content.Context
import com.example.mlbweatherforecast.data.network.NetworkCheckerImpl
import com.example.mlbweatherforecast.domain.utilities.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkChecker(
        @ApplicationContext
        context: Context
    ): NetworkChecker {
        return NetworkCheckerImpl(context)
    }
}