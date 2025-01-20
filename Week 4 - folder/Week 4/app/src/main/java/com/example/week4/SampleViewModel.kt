package com.example.week4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SampleViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    private var number = 0

    val badgeCount: LiveData<Int>
        get() = _count

    init {
        _count.value = number
    }

    fun incrementBadgeCount() {
        number++
        _count.postValue(number)
    }

    override fun onCleared() {
        super.onCleared()
    }
}