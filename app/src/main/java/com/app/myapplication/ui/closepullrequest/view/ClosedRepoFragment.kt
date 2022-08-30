package com.app.myapplication.ui.closepullrequest.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.base.ui.BaseInternetFragment
import com.app.myapplication.databinding.FragmentClosedRepoBinding
import com.app.myapplication.ui.closepullrequest.view.adapter.AppRecyclerAdapter
import com.app.myapplication.ui.closepullrequest.viewmodel.ClosedRepoViewModel
import com.app.myapplication.util.extension.getServerError
import com.app.myapplication.util.extension.showLongToast
import com.app.myapplication.util.extension.toPx
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ClosedRepoFragment : BaseInternetFragment() {

    private val recyclerAdapter by lazy {
        AppRecyclerAdapter()
    }
    private var _binding: FragmentClosedRepoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var moshi: Moshi

    private val viewModel by viewModels<ClosedRepoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClosedRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        setupView()
        addObservers()
        loadData()
    }

    override fun onInternetConnected() {
//        if (viewModel.closedRepoLiveData.value.isNullOrEmpty()) {
//            loadData()
//        }
        if (recyclerAdapter.itemCount == 0) {
            recyclerAdapter.retry()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView() {
        binding.rvMeme.apply {
            adapter = recyclerAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val value = 8.toPx
                    outRect.set(value, value, value, value)
                }
            })
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    super.onScrollStateChanged(recyclerView, newState)
//                }
//
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    if (dy >= 0) {
//                        if (!viewModel.isPaginating) {
//                            val layoutManager =
//                                recyclerView.layoutManager as? LinearLayoutManager ?: return
//                            val lastVisibleItemPosition =
//                                layoutManager.findLastVisibleItemPosition()
//                            if (lastVisibleItemPosition >= (recyclerAdapter.itemCount - 5)) {
//                                viewModel.paginateClosedRepoList()
//                            }
//                        }
//                    }
//                }
//            })
        }
        binding.btnRetry.setOnClickListener {
            recyclerAdapter.retry()
//            viewModel.fetchClosedRepoList()
        }
    }

    private fun addObservers() {
//        viewModel.closedRepoLiveData.observe(viewLifecycleOwner) {
//            recyclerAdapter.submitData(it.orEmpty()) {
//                viewModel.stopPaginating()
//            }
//        }
        viewModel.repoLiveData.observe(viewLifecycleOwner) {
            recyclerAdapter.submitData(lifecycle, it)
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            recyclerAdapter.loadStateFlow.collect { combinedLoadStates ->
                binding.clError.isVisible = combinedLoadStates.source.refresh is LoadState.Error
                binding.rvMeme.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
                binding.cpiLoader.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
                binding.flLoadMore.isVisible = combinedLoadStates.source.append is LoadState.Loading

                (combinedLoadStates.source.refresh as? LoadState.Error)?.let { error ->
                    binding.tvError.text =
                        error.error.getServerError(moshi)?.message ?: error.error.message
                }
                (combinedLoadStates.source.append as? LoadState.Error)?.let { error ->
                    requireContext().showLongToast(
                        error.error.getServerError(moshi)?.message ?: error.error.message
                    )
                }
            }
        }
    }
}