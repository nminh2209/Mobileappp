package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var btnLogin: Button
lateinit var txtUser: EditText
lateinit var txtPass: EditText

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        btnLogin = findViewById(R.id.btnLogin)
        txtUser = findViewById(R.id.txtUser)
        txtPass = findViewById(R.id.txtPass)

        // Set click listener
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                val username = txtUser.text.toString()
                val password = txtPass.text.toString()

                if (username == "nminh2209" && password == "123") {
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Not correct", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
