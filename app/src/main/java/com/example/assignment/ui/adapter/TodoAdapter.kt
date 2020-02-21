package com.example.assignment.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.network.NetworkState
import com.example.assignment.ui.model.TodoResponseModel

/**
 * A RecyclerView Adapter that sets data for eact item.
 */
class TodoAdapter : ListAdapter<TodoResponseModel,
        RecyclerView.ViewHolder>(diff) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_layout_todo -> (holder as TodoViewHolder).bindData(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_layout_todo -> TodoViewHolder.create(parent)

            else -> throw IllegalStateException("unknow view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_layout_todo
    }


    companion object {
        val diff = object : DiffUtil.ItemCallback<TodoResponseModel>() {
            override fun areItemsTheSame(
                oldItem: TodoResponseModel,
                newItem: TodoResponseModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TodoResponseModel,
                newItem: TodoResponseModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}