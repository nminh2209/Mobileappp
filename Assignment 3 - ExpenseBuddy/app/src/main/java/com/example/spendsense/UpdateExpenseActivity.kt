package com.example.spendsense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.spendsense.databinding.ActivityUpdateExpenseBinding
import java.text.SimpleDateFormat
import java.util.*

class UpdateExpenseActivity : AppCompatActivity() {

    // ViewBinding object to access UI elements
    private lateinit var binding: ActivityUpdateExpenseBinding

    // Database helper instance
    private lateinit var db: ExpenseDatabaseHelper

    // To hold the ID of the expense being edited
    private var expenseId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityUpdateExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database
        db = ExpenseDatabaseHelper(this)

        // Retrieve the passed expense ID from the intent
        expenseId = intent.getIntExtra("expense_id", -1)
        if (expenseId == -1) {
            // If no valid ID was passed, close the activity
            finish()
            return
        }

        // Fetch the expense data from the database
        val expense = db.getExpenseByID(expenseId)

        // Pre-fill the input fields with existing expense data
        binding.updateAmount.setText(expense.amount.toString())
        binding.updateContentEditText.setText(expense.content)
        binding.updateDateEditText.setText(expense.date)

        // Set click listener to save the updated data
        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateAmount.text.toString()
            val newTitleInt = newTitle.toIntOrNull() ?: 0 // Safely convert input to Int
            val newContent = binding.updateContentEditText.text.toString()

            // Format date string using custom function
            val date = formatDate(binding.updateDateEditText.text.toString())

            // Create updated expense object
            val updatedExpense = Expense(expenseId, newTitleInt, newContent, date)

            // Update the database
            db.updateExpense(updatedExpense)

            // Close the activity and notify user
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
