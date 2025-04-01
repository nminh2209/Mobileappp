package com.example.musicplayer.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY title ASC")
    fun getAllSongs(): LiveData<List<SongEntity>>  // Must return LiveData for reactivity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: SongEntity)

    @Update
    suspend fun update(song: SongEntity)

    @Delete
    suspend fun delete(song: SongEntity)
}
