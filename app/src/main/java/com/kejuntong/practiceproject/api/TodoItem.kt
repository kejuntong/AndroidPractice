package com.kejuntong.practiceproject.api

data class TodoItem (
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)