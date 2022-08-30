package com.app.myapplication.ui.closepullrequest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.myapplication.model.RepoResponse
import com.app.myapplication.repo.AppRepo
import com.app.myapplication.util.Status
import kotlin.math.max

class PullRequestPageSource(
    private val owner: String,
    private val repo: String,
    private val appRepo: AppRepo
) : PagingSource<Int, RepoResponse>() {

    private var page = STARTING_KEY
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoResponse> {
        page = params.key ?: STARTING_KEY
        val perPage = params.loadSize
        return try {
            val result =
                appRepo.getClosedRepoList(
                    owner = owner,
                    repo = repo,
                    perPage = perPage,
                    page = page
                )
            if (result.status == Status.SUCCESS) {
                LoadResult.Page(
                    data = result.data.orEmpty(),
                    prevKey = null,
                    nextKey = if (result.data.isNullOrEmpty()) null else {
                        ++page
                    }
                )
            } else {
                LoadResult.Error(throwable = Throwable(result.message))
            }
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, RepoResponse>): Int? {

        return ensureValidKey(page)
//            ?.let { anchorPosition -> state.closestItemToPosition(anchorPosition) }
//            ?.let { item -> dataSource.getKeyInternal(item) }
    }

    companion object {
        private const val STARTING_KEY = 1
    }

}