package com.example.musi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var usernameTextView: TextView
    private lateinit var creditsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        usernameTextView = findViewById(R.id.username)
        creditsTextView = findViewById(R.id.credits)

        val username = intent.getStringExtra("username")
        val credits = intent.getIntExtra("credits", 0)

        usernameTextView.text = "Username: $username"
        creditsTextView.text = "Credits: $credits"
    }
}