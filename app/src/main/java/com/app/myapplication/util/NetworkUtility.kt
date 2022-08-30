package com.app.myapplication.util

import android.content.Context
import android.net.ConnectivityManager


object NetworkUtility {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnected == true
    }
}