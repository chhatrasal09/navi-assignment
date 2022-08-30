package com.app.myapplication.util.extension

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter(
    "android:visibility"
)
fun View?.setVisibility(visible: Boolean) {
    if (this == null) return
    isVisible = visible
}