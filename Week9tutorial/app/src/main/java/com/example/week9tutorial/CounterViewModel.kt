package com.example.week9tutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _taps = MutableLiveData(0)
    val taps: LiveData<Int> = _taps

    private var tapCount = 0

    fun updateTaps() {
        viewModelScope.launch {
            delay(3000) // Pause for 3 seconds
            _taps.value = ++tapCount // Update counter safely
        }
    }
}
