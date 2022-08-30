package com.app.myapplication.base.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseInternetActivity : AppCompatActivity() {

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            internetStatus(available = true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            internetStatus(available = false)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachNetworkChangeObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachNetworkChangeObserver()
    }

    private fun attachNetworkChangeObserver() {
        (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build(), networkCallback
        )
    }

    private fun detachNetworkChangeObserver() {
        (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.unregisterNetworkCallback(
            networkCallback
        )
    }

    open fun internetStatus(available: Boolean) {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isActivityTransitionRunning)
            || isFinishing || isDestroyed
        )
            return
        if (available)
            BaseInternetFragment.sendInternetConnectedBroadcast(this)
    }

}