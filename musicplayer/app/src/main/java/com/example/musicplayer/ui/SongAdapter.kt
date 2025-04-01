package com.example.musicplayer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.data.database.SongEntity
import com.example.musicplayer.databinding.ItemSongBinding

class SongAdapter : ListAdapter<SongEntity, SongAdapter.SongViewHolder>(SongDiffCallback()) {

    class SongViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: SongEntity) {
            binding.songListTitle.text = song.title
            binding.songListArtist.text = song.artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SongDiffCallback : DiffUtil.ItemCallback<SongEntity>() {
    override fun areItemsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean = oldItem == newItem
}
