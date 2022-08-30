package com.app.myapplication.ui.closepullrequest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.databinding.ItemPullRequestBinding
import com.app.myapplication.model.RepoResponse

class AppRecyclerAdapter :
//    RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder>() {
    PagingDataAdapter<RepoResponse, AppRecyclerAdapter.ViewHolder>(
        ITEM_CALLBACK
    ) {

//    private val differ by lazy { AsyncListDiffer(this, ITEM_CALLBACK) }
//    private val list get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

//    override fun getItemCount(): Int = list.size

//    fun submitData(list: List<ApiResponseItem>, callback: (() -> Unit)? = null) {
//        differ.submitList(list) {
//            callback?.invoke()
//        }
//    }

    class ViewHolder(private val binding: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RepoResponse?) {
            binding.repoItem = item
        }
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<RepoResponse>() {
            override fun areContentsTheSame(
                oldItem: RepoResponse,
                newItem: RepoResponse
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: RepoResponse,
                newItem: RepoResponse
            ): Boolean = oldItem.id == newItem.id
        }
    }
}