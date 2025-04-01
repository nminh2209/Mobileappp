package com.example.musicplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.data.database.SongEntity
import com.example.musicplayer.data.repository.SongRepository
import kotlinx.coroutines.launch

class SongViewModel(private val repository: SongRepository) : ViewModel() {

    val allSongs: LiveData<List<SongEntity>> = repository.allSongs

    fun insert(song: SongEntity) = viewModelScope.launch {
        repository.insert(song)
    }

    fun update(song: SongEntity) = viewModelScope.launch {
        repository.update(song)
    }

    fun delete(song: SongEntity) = viewModelScope.launch {
        repository.delete(song)
    }
}
