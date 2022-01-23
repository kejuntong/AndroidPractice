package com.kejuntong.practiceproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kejuntong.practiceproject.api.ApiFactory
import com.kejuntong.practiceproject.api.TodoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoListViewModel : ViewModel() {

    private val todoListLiveData: MutableLiveData<List<TodoItem>> = MutableLiveData()
    internal fun getTodoList(): LiveData<List<TodoItem>> = todoListLiveData

    internal fun retrieveTodoList() {
        ApiFactory.getTodoList().enqueue(object : Callback<List<TodoItem>> {
            override fun onResponse(
                call: Call<List<TodoItem>>,
                response: Response<List<TodoItem>>
            ) {
                response.body()?.apply {
                    todoListLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<TodoItem>>, t: Throwable) {
                Log.w(TAG, "retrieveTodoList failure - ${t.message}")
            }
        })
    }

    private val selectedItemLiveData: MutableLiveData<TodoItem> = MutableLiveData()
    internal fun getSelectedItem(): LiveData<TodoItem> = selectedItemLiveData

    internal fun selectItem(item: TodoItem) {
        selectedItemLiveData.postValue(item)
    }

    companion object {
        private val TAG = TodoListViewModel::class.java.simpleName
    }
}