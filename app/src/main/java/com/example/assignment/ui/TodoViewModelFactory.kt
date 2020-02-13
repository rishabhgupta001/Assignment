package com.example.assignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.data.repository.TodoRepository

@Suppress("UNCHECKED_CAST")
class TodoViewModelFactory(private val repositry: TodoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(repositry) as T
    }
}