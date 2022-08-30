package com.app.myapplication.base.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseInternetFragment : Fragment() {

    private val interStartedBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (ACTION_INTERNET_CONNECTED.equals(intent?.action, ignoreCase = true))
                onInternetConnected()
        }
    }

    abstract fun onInternetConnected()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.registerReceiver(
            interStartedBroadcastReceiver,
            IntentFilter(ACTION_INTERNET_CONNECTED)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(interStartedBroadcastReceiver)
    }

    companion object {
        private const val ACTION_INTERNET_CONNECTED =
            "com.app.myapplication.action.INTENT_CONNECTED"

        fun sendInternetConnectedBroadcast(context: Context) {
            Intent.ACTION_ANSWER
            context.sendBroadcast(Intent(ACTION_INTERNET_CONNECTED))
        }
    }
}