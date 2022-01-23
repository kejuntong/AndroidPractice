package com.kejuntong.practiceproject.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApis {

    @GET("todos")
    fun getTodoList(): Call<List<TodoItem>>

    @GET("todos/{todoItemId}")
    fun getTodoItemDetails(@Path("todoItemId") id: Int): Call<TodoItemDetails>
}