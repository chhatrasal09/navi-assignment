package com.app.myapplication.util.extension

import android.os.Build
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.myapplication.util.UiState
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("time", "prependText", "appendText", requireAll = false)
fun TextView?.setTime(timeStr: String?, prependText: String?, appendText: String?) {
    if (this == null || timeStr.isNullOrEmpty()) return
    val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Locale.getDefault(Locale.Category.FORMAT)
    else
        Locale.getDefault()
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)
    val formatter = SimpleDateFormat("dd-MMM-yyyy HH:mm aa", locale)
    var output: String = formatter.format(parser.parse(timeStr) ?: return)
    if (!prependText.isNullOrEmpty())
        output = prependText + output
    if (!appendText.isNullOrEmpty())
        output += appendText
    text = output
}

@BindingAdapter("errorMessage")
fun TextView?.setErrorMessage(errorUiState: UiState?) {
    if (this == null) return
    if (errorUiState is UiState.Error)
        text = errorUiState.message
}