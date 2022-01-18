package com.kejuntong.practiceproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel : ViewModel() {

    private val testData: MutableLiveData<Int> = MutableLiveData()
    internal fun getTestData(): LiveData<Int> = testData

    internal fun setTestData(data: Int) {
        testData.postValue(data)
    }
}