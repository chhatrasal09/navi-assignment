package com.app.myapplication.repo

import com.app.myapplication.model.RepoResponse
import com.app.myapplication.util.Resource

interface AppRepo {

    suspend fun getClosedRepoList(
        owner: String,
        repo: String,
        perPage: Int,
        page: Int
    ): Resource<List<RepoResponse>>
}