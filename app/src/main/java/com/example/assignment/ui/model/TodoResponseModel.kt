package com.example.assignment.ui.model

data class TodoResponseModel(
    val completed: Boolean?,
    val id: Int?,
    val title: String?,
    val userId: Int?
)