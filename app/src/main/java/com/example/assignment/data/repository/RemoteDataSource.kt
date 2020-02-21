package com.example.assignment.data.repository

import com.example.assignment.data.network.ApiService
import com.example.assignment.ui.model.TodoResponseModel


/**
 *
​
 * Purpose – Class contains list of all the network api's
​
 * @author ​Rishabh Gupta
​
 * Created on February 21, 2020
​
 * Modified on February 21, 2020
 *
 * */
class RemoteDataSource(private val api: ApiService) {

    //get todo response from network
    suspend fun getTodo(): List<TodoResponseModel>? = api.getTodoList2()!!

}