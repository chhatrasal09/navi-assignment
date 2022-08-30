package com.app.myapplication.util.extension

import android.content.Context
import android.widget.Toast

fun Context?.showLongToast(message: String?) {
    if (this == null || message.isNullOrEmpty()) return
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}