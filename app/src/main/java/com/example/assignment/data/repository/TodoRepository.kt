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
    val todoList = MutableLiveData<List<TodoResponseModel>>()
    val networkState = MutableLiveData<NetworkState>()

    @SuppressLint("CheckResult")
    override fun listOfTodo(): ListingDataModel<TodoResponseModel> {

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
                    Log.d("DEBUG", "api error ${error.message}")
                    networkState.value = NetworkState.error(error.message!!)
                })

        return ListingDataModel(
            todoList = todoList,
            networkState = networkState
        )
    }

}