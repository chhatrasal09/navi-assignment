package com.app.myapplication.repo

import com.app.myapplication.model.RepoResponse
import com.app.myapplication.util.Resource
import javax.inject.Inject

class AppRepoImpl @Inject constructor(private val webService: WebService) : AppRepo {

    override suspend fun getClosedRepoList(
        owner: String,
        repo: String,
        perPage: Int,
        page: Int
    ): Resource<List<RepoResponse>> {
        val response = webService.getClosedRepoPullList(
            owner = owner,
            repo = repo,
            per_page = perPage,
            page = page
        )
        return if (response.isSuccessful) {
            Resource.success(response.body().orEmpty())
        } else {
            Resource.error(response.errorBody()?.string().orEmpty())
        }
    }
}