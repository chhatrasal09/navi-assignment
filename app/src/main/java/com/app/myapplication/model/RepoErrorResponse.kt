package com.app.myapplication.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RepoErrorResponse(
    @field:Json(name = "message")
    val message: String?
) : Parcelable