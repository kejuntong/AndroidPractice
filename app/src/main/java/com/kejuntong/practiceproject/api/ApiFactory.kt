package com.kejuntong.practiceproject.api

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    private val api by lazy {
        retrofit.create(JsonPlaceholderApis::class.java)
    }

    internal fun getTodoList(): Call<List<TodoItem>> {
        return api.getTodoList()
    }

    internal fun getTodoListDetails(itemId: Int): Call<TodoItemDetails> {
        return api.getTodoItemDetails(itemId)
    }
}