package com.kejuntong.practiceproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kejuntong.practiceproject.api.TodoItemDetails
import com.kejuntong.practiceproject.viewmodel.TodoDetailsViewModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoAnnotations

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class TodoDetailsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val observer: Observer<TodoItemDetails> = mock()
    private val mockedCall: Call<TodoItemDetails> = mock()

    private val todoDetailsViewModel = object : TodoDetailsViewModel() {
        override fun getTodoListCall(itemId: Int): Call<TodoItemDetails> {
            return mockedCall
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        todoDetailsViewModel.getTodoItemDetails().observeForever(observer)
    }

    @Test
    fun retrieveTodoItemDetails_whenCallSuccess_shouldPostTodoItemDetails() {
        doAnswer { invocation ->
            val callback: Callback<TodoItemDetails> = invocation.getArgument(0, Callback::class.java) as Callback<TodoItemDetails>
            callback.onResponse(mockedCall, Response.success(TodoItemDetails(1, 1, "test title", true)))
            // or callback.onResponse(mockedCall, Response.error(404. ...);
            // or callback.onFailure(mockedCall, new IOException());
        }.whenever(mockedCall).enqueue(any())

        todoDetailsViewModel.retrieveTodoItemDetails(1)
        val captor = ArgumentCaptor.forClass(TodoItemDetails::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(1, value.id)
        }
    }
}