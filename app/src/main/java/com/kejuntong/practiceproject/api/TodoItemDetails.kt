package com.kejuntong.practiceproject.api

// this looks same as TodoItem class
// here just use a separate class
// to represent a different call response
data class TodoItemDetails(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)