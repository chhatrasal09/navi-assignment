package com.app.myapplication.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RepoResponse(
    @field:Json(name = "closed_at")
    var closedAt: String?,
    @field:Json(name = "created_at")
    var createdAt: String?,
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "state")
    var state: String?,
    @field:Json(name = "title")
    var title: String?,
    @field:Json(name = "updated_at")
    var updatedAt: String?,
    @field:Json(name = "url")
    var url: String?,
    @field:Json(name = "user")
    var user: RepoUser?
) : Parcelable