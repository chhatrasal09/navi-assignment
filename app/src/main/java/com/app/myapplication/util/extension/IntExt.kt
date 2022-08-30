package com.app.myapplication.util.extension

import android.content.res.Resources
import kotlin.math.ceil

val Int?.toPx: Int
    get() = if (this == null) 0 else ceil(this * Resources.getSystem().displayMetrics.density).toInt()

val Int?.toFloatDp: Int
    get() = if (this == null) 0 else ceil(this / Resources.getSystem().displayMetrics.density).toInt()