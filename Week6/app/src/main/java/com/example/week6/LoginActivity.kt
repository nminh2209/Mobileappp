package com.example.week6

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameField: EditText = findViewById(R.id.emailEditText)
        val passwordField: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            if (validateLogin(username, password)) {
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE)
        val savedPassword = sharedPreferences.getString("admin_password", "admin123") ?: "admin123"

        println("DEBUG: Saved password -> $savedPassword | Entered password -> $password")

        return username == "admin" && password == savedPassword
    }


}
