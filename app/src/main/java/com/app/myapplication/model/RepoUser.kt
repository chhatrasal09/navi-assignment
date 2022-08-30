package com.app.myapplication.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RepoUser(
    @field:Json(name = "avatar_url")
    var avatarUrl: String?,
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "login")
    var login: String?,
    @field:Json(name = "name")
    var name: String?,
    @field:Json(name = "site_admin")
    var siteAdmin: Boolean = false,
    @field:Json(name = "type")
    var type: String?,
) : Parcelable