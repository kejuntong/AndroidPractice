package com.kejuntong.practiceproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kejuntong.practiceproject.api.ApiFactory
import com.kejuntong.practiceproject.api.TodoItemDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoDetailsViewModel : ViewModel() {

    private val todoItemDetailsLiveData = MutableLiveData<TodoItemDetails>()
    internal fun getTodoItemDetails(): LiveData<TodoItemDetails> = todoItemDetailsLiveData

    internal fun retrieveTodoItemDetails(itemId: Int) {
        ApiFactory.getTodoListDetails(itemId).enqueue(object : Callback<TodoItemDetails>{
            override fun onResponse(
                call: Call<TodoItemDetails>,
                response: Response<TodoItemDetails>
            ) {
                response.body()?.apply {
                    todoItemDetailsLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<TodoItemDetails>, t: Throwable) {
                Log.w(TAG, "todo item details call failure, ${t.message}")
            }
        })
    }

    companion object {
        private val TAG = TodoDetailsViewModel::class.java.simpleName
    }
}