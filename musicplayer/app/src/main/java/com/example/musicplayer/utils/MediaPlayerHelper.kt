package com.example.musicplayer.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.musicplayer.data.database.SongEntity

object MediaPlayerHelper {
    private var mediaPlayer: MediaPlayer? = null

    fun playSong(context: Context, song: SongEntity) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(song.filePath)
            prepare()
            start()
        }
    }

    fun pauseSong() {
        mediaPlayer?.pause()
    }

    fun stopSong() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
