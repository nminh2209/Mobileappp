package com.example.musicplayer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.data.database.SongDatabase
import com.example.musicplayer.data.repository.SongRepository
import com.example.musicplayer.databinding.FragmentLibraryBinding
import com.example.musicplayer.viewmodel.SongViewModel
import com.example.musicplayer.viewmodel.SongViewModelFactory

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private lateinit var songViewModel: SongViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Database & Repository
        val songDao = SongDatabase.getDatabase(requireContext()).songDao()
        val repository = SongRepository(songDao)

        // Initialize ViewModel using ViewModelProvider
        songViewModel = ViewModelProvider(this, SongViewModelFactory(repository))[SongViewModel::class.java]

        // Set up RecyclerView
        val adapter = SongAdapter()
        binding.songRecyclerView.adapter = adapter
        binding.songRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData
        songViewModel.allSongs.observe(viewLifecycleOwner) { songs ->
            adapter.submitList(songs)
        }
    }
}
