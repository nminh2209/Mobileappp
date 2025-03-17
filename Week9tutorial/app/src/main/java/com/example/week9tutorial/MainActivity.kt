package com.example.week9tutorial

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.week9tutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe LiveData
        viewModel.taps.observe(this) { count ->
            binding.counterText.text = count.toString()
        }

        // Button Click Listener
        binding.counterButton.setOnClickListener {
            viewModel.updateTaps()
        }
    }
}
