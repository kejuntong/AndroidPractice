package com.kejuntong.practiceproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kejuntong.practiceproject.api.ApiFactory
import com.kejuntong.practiceproject.api.TodoItemDetails
import com.kejuntong.practiceproject.api.UserDetails
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

    private val userDetailsLiveData = MutableLiveData<UserDetails>()
    internal fun getUserDetails(): LiveData<UserDetails> = userDetailsLiveData

    internal fun retrieveUserDetails(userId: Int) {
        ApiFactory.getUserDetails(userId).enqueue(object : Callback<UserDetails> {
            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                response.body()?.apply {
                    userDetailsLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Log.w(TAG, "user details call failure, ${t.message}")
            }
        })
    }

    companion object {
        private val TAG = TodoDetailsViewModel::class.java.simpleName
    }
}