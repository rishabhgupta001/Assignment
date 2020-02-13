package com.example.assignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.data.repository.TodoRepository

/**
 *
 *
 *
​
 * Purpose – ViewModel
​
 * @author ​Rishabh Gupta
​
 * Created on Feburary 13, 2020
​
 * Modified on Feburary 13, 2020
 *
 * */

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val subTodo = MutableLiveData<String>()
}