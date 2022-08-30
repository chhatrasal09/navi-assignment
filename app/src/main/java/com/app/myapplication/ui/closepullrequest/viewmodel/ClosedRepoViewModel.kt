package com.app.myapplication.ui.closepullrequest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.app.myapplication.base.BaseViewModel
import com.app.myapplication.model.RepoResponse
import com.app.myapplication.repo.AppRepo
import com.app.myapplication.ui.closepullrequest.paging.PullRequestPageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClosedRepoViewModel @Inject constructor(
    private val appRepo: AppRepo
) : BaseViewModel() {

//    private val TAG = "MainActivityViewModel"
//    private val _closedRepoLiveData: MutableLiveData<List<ApiResponseItem>> =
//        MutableLiveData(emptyList())
//    val closedRepoLiveData: LiveData<List<ApiResponseItem>> = _closedRepoLiveData

//    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
//    val uiState: LiveData<UiState> = _uiState

    val repoLiveData: LiveData<PagingData<RepoResponse>> = Pager(
        config = PagingConfig(
            pageSize = PER_PAGE_LIMIT,
            initialLoadSize = PER_PAGE_LIMIT,
            enablePlaceholders = true,
        ),
        pagingSourceFactory = { PullRequestPageSource("bumptech", "glide", appRepo) }
    ).liveData.cachedIn(viewModelScope)

//    private var closedRepoApiPerPage = PER_PAGE_LIMIT
//    private var closedRepoApiPage = PAGE_START

//    @Volatile
//    @get:Bindable
//    var isPaginating: Boolean = false
//        private set(value) {
//            field = value
//            notifyPropertyChanged(BR.paginating)
//        }

//    fun fetchClosedRepoList() {
//        viewModelScope.launch {
////            if (!isPaginating)
////                _uiState.postValue(UiState.Loading)
//            try {
//                val result = appRepo.getClosedRepoList(
//                    owner = "bumptech",
//                    repo = "glide",
//                    page = closedRepoApiPage,
//                    perPage = closedRepoApiPerPage
//                )
//                when (result.status) {
//                    Status.SUCCESS -> {
//                        _closedRepoLiveData.postValue(
//                            _closedRepoLiveData.value.orEmpty().toMutableList().let {
//                                it.addAll(result.data.orEmpty())
//                                it.distinctBy { item -> item.id }
//                            })
////                        if (!isPaginating)
////                            _uiState.postValue(UiState.Success)
//                    }
//                    Status.ERROR -> {
////                        if (!isPaginating)
////                            _uiState.postValue(UiState.Error(result.message))
//                    }
//                    Status.LOADING -> Unit
//                }
//            } catch (e: Exception) {
//                closedRepoApiPage--
////                if (!isPaginating)
////                    _uiState.postValue(UiState.Error(e.message))
//                stopPaginating()
//            }
//        }
//    }

//    fun paginateClosedRepoList() {
//        if (isPaginating) return
//        closedRepoApiPage++
//        isPaginating = true
//        Log.d(TAG, " pagination started for page = $closedRepoApiPage")
//        fetchClosedRepoList()
//    }

//    fun stopPaginating() {
//        isPaginating = false
//        Log.d(TAG, " pagination stoped for page = $closedRepoApiPage")
//    }


    companion object {
        private const val PER_PAGE_LIMIT = 30
    }
}