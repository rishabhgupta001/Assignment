package com.example.assignment.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.assignment.data.network.ApiService
import com.example.assignment.data.network.NetworkState
import com.example.assignment.data.network.StatusCode
import com.example.assignment.ui.model.ListingDataModel
import com.example.assignment.ui.model.TodoResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
​
 * Purpose – Repository
 *
 * @author ​Rishabh Gupta
​
 * Created on Feburary 13, 2020
​
 * Modified on Feburary 13, 2020
 *
 * */

class TodoRepository(private val api: ApiService) : TodoListRepository {
    val TAG = TodoRepository::class.java.simpleName
    val todoList = MutableLiveData<List<TodoResponseModel>>()
    val networkState = MutableLiveData<NetworkState>()

    override fun listOfTodo(): ListingDataModel<TodoResponseModel> {

        val todoPair = fetchTodoList()

        return ListingDataModel(
            todoList = todoPair.first,
            networkState = todoPair.second
        )
    }

    @SuppressLint("CheckResult")
    private fun fetchTodoList(): Pair<MutableLiveData<List<TodoResponseModel>>, MutableLiveData<NetworkState>> {
        api.getTodoList()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                networkState.postValue(NetworkState.START)
            }
            .observeOn(AndroidSchedulers.mainThread())
            //below code is working on Main thread
            .subscribe(
                { success ->
                    networkState.value = NetworkState.SUCCESS
                    todoList.value = success
                }, { error ->
                    Log.d(TAG, "api error ${error.message}")
                    networkState.value = NetworkState.error(error.message!!)
                })

        return Pair(todoList, networkState)
    }

}