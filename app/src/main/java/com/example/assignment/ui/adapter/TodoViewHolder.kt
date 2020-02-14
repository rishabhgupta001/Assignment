package com.example.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.databinding.ItemLayoutTodoBinding
import com.example.assignment.ui.model.TodoResponseModel

/**
 * A RecyclerView ViewHolder that displays a Delivery post.
 */
class TodoViewHolder(val binding: ItemLayoutTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener{

        }
    }

    fun bindData(data: TodoResponseModel) {
        binding.data = data
    }

    companion object {
        fun create(parent: ViewGroup): TodoViewHolder {
            //item_layout_todo layout inflated
            val binding =
                DataBindingUtil.inflate<ItemLayoutTodoBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_layout_todo,
                    parent,
                    false
                )
            return TodoViewHolder(binding)
        }
    }
}