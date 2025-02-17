package com.example.week6

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val changePasswordButton: Button = findViewById(R.id.changePasswordButton)
        changePasswordButton.setOnClickListener {
            showChangePasswordDialog()
        }

        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {

        val sharedPreferences: SharedPreferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("admin_logged_in")
            apply()
        }


        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }



    private fun showChangePasswordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_change_password, null)
        val currentPasswordField: EditText = dialogView.findViewById(R.id.currentPasswordField)
        val newPasswordField: EditText = dialogView.findViewById(R.id.newPasswordField)
        val confirmPasswordField: EditText = dialogView.findViewById(R.id.confirmPasswordField)

        val sharedPreferences: SharedPreferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE)
        val savedPassword = sharedPreferences.getString("admin_password", "admin123") ?: "admin123"

        AlertDialog.Builder(this)
            .setTitle("Change Password")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val currentPassword = currentPasswordField.text.toString()
                val newPassword = newPasswordField.text.toString()
                val confirmPassword = confirmPasswordField.text.toString()

                if (currentPassword == savedPassword) {
                    if (newPassword.isNotEmpty() && newPassword == confirmPassword) {
                        saveNewPassword(newPassword)
                        Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "New passwords do not match!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Current password is incorrect!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveNewPassword(password: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("admin_password", password)
            apply()
        }

        val savedPassword = sharedPreferences.getString("admin_password", "admin123")
        println("DEBUG: New password saved -> $savedPassword")
    }


}


