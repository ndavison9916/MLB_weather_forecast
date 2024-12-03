package com.example.mlbweatherforecast.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.mlbweatherforecast.domain.utilities.NetworkChecker
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Class which implements the network checker. Returns whether the app has access to the internet.
 */
class NetworkCheckerImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
    ) : NetworkChecker {
    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}