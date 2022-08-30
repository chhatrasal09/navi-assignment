package com.app.myapplication.util.extension

import com.app.myapplication.model.RepoErrorResponse
import com.squareup.moshi.Moshi

fun Throwable.getServerError(moshi: Moshi): RepoErrorResponse? {
    return try {
        moshi.adapter(RepoErrorResponse::class.java).fromJson(message ?: "")
    } catch (e: Exception) {
        null
    }
}