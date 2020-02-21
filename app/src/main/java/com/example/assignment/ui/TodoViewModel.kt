package com.example.assignment.ui

import android.os.Handler
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.assignment.data.repository.TodoRepository
import kotlinx.android.synthetic.main.fragment_todo_list.*

/**
 *
​
 * Purpose – ViewModel
​
 * @author ​Rishabh Gupta
​
 * Created on Feburary 13, 2020
​
 * Modified on Feburary 14, 2020
 *
 * */

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val subTodoName = MutableLiveData<String>()
    //map:- everytime the value of subTodoName changes, repoResult will be updated too. (Here)
    val repoResult = Transformations.map(subTodoName) { repository.listOfTodo() }


    /**
     * SwitchMap:- everytime the value of repoResult changes, it.todoList will be called,
     * just like the map function. But it.todoList returns a LiveData. So everytime that
     * the value of the LiveData returned by it.todoList changes, the value of post
     * will change too. So the value of post will depend on changes of repoResult and changes of
     * the value of it.todoList.
     */
    val todoList = Transformations.switchMap(repoResult) { it.todoList }
    val networkState = Transformations.switchMap(repoResult) { it.networkState }

    /**
     * On Pull to Refresh from Ui
     */
    fun refresh() {
        subTodoName.value = null
    }

}