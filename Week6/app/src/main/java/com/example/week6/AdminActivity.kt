package com.example.week6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val adminProfileImage: ImageView = findViewById(R.id.adminProfileImage)
        val adminName: TextView = findViewById(R.id.adminName)
        val logoutButton: Button = findViewById(R.id.logoutButton)


        adminName.text = "Admin User"

        adminProfileImage.setImageResource(R.drawable.admin_placeholder)

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
