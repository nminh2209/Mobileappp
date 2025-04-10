package com.example.spendsense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.spendsense.databinding.ActivityAddExpenseBinding
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    // ViewBinding object to access layout views
    private lateinit var binding: ActivityAddExpenseBinding

    // Database helper instance to manage expense operations
    private lateinit var db: ExpenseDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = ExpenseDatabaseHelper(this)

        // Handle save button click
        binding.saveButton.setOnClickListener {
            val title = binding.amount.text.toString()
            val titleAsInteger = title.toIntOrNull() ?: 0 // Convert to Int safely
            val content = binding.contentEditText.text.toString()

            // Format the date input from user
            val date = formatDate(binding.dateEditText.text.toString())

            // Create a new Expense object (ID is 0 — auto-increment handled by DB)
            val expense = Expense(0, titleAsInteger, content, date)

            // Insert the expense into the database
            db.insertExpense(expense)

            // Close the activity and show confirmation
            finish()
            Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
