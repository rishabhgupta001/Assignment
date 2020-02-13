package com.example.assignment.data.repository

import com.example.assignment.ui.model.ListingDataModel
import com.example.assignment.ui.model.TodoResponseModel

/**
 *
​
 * Purpose – Making common interface that can be shared by the different repository implementations.
 *
 * @author ​Rishabh Gupta
​
 * Created on Feburary 13, 2020
​
 * Modified on Feburary 13, 2020
 *
 * */
interface TodoListRepository {

    fun listOfTodo(): ListingDataModel<TodoResponseModel>
}