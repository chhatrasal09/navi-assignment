package com.app.myapplication.repo

import com.app.myapplication.model.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getClosedRepoPullList(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("per_page") per_page: Int = 2,
        @Query("page") page: Int = 2,
        @Query("state") state: String = "closed"
    ): Response<List<RepoResponse>>
}