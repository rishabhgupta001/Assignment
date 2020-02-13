package com.example.assignment.ui.model

import androidx.lifecycle.LiveData
import com.example.assignment.data.network.NetworkState

/**
 *
​
 * Purpose – Data class that is necessary for a UI to show a listing and interact with network and user
 *
 * @author ​Rishabh Gupta
​
 * Created on Feburary 13, 2020
​
 * Modified on Feburary 13, 2020
 *
 * */

data class ListingDataModel<T>(
    // the LiveData of paged lists for the UI to observe into Recyclerview
    val todoList: LiveData<List<TodoResponseModel>>,
    //represent network request status
    val networkState: LiveData<NetworkState>
)