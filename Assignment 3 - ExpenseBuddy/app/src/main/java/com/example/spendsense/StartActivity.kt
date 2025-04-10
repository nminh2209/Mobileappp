package com.example.spendsense

import android.widget.ImageView
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.spendsense.databinding.ActivityStartBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class StartActivity : AppCompatActivity() {

    // ViewBinding object to access layout views safely
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load a GIF (e.g., pig animation) into the ImageView using Glide
        Glide.with(this)
            .asGif() // Treat the resource as a GIF
            .load(R.drawable.pig) // Resource to load
            .into(binding.gifImageView) // Target ImageView

        // Delay for 3.5 seconds, then navigate to MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish StartActivity so it doesn’t stay in the back stack
        }, 3500) // Delay in milliseconds (3500ms = 3.5 seconds)
    }
}
