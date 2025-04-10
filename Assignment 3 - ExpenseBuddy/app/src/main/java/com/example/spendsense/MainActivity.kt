package com.example.spendsense

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendsense.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // View binding to access views from activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Database helper for accessing expense data
    private lateinit var db: ExpenseDatabaseHelper

    // RecyclerView adapter to show the list of expenses
    private lateinit var expenseAdapter: ExpenseAdapter

    // SharedPreferences to store and retrieve the user’s budget
    private lateinit var prefs: SharedPreferences
    private val defaultBudget = 10000  // Default monthly budget if none is set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences for persistent key-value storage
        prefs = getSharedPreferences("spendsense_prefs", MODE_PRIVATE)

        // Initialize local SQLite database and fetch expenses
        db = ExpenseDatabaseHelper(this)
        expenseAdapter = ExpenseAdapter(db.getAllExpenses(), this)

        // Set up RecyclerView with vertical layout and adapter
        binding.expensesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.expensesRecyclerView.adapter = expenseAdapter

        // Handle "Add" FAB click: open the activity to add a new expense
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // Handle budget edit icon click: show dialog to update budget
        binding.editBudgetIcon.setOnClickListener {
            val dialog = EditBudgetDialog(this, getBudget()) { newBudget ->
                setBudget(newBudget) // Save new budget
                updateRemainingBalance() // Recalculate remaining
                Toast.makeText(this, "Budget updated to $$newBudget", Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }

        // Show remaining balance on launch
        updateRemainingBalance()
    }

    // Refresh data every time the user returns to the main screen
    override fun onResume() {
        super.onResume()
        expenseAdapter.refreshData(db.getAllExpenses())
        updateRemainingBalance()
    }

    // Calculate and update the text view showing remaining budget
    private fun updateRemainingBalance() {
        val totalSpent = db.getTotalExpenses()
        val remaining = getBudget() - totalSpent
        binding.remainingBalanceTextView.text = "Remaining: $${remaining}"
    }

    // Retrieve saved budget or return the default
    private fun getBudget(): Int {
        return prefs.getInt("monthly_budget", defaultBudget)
    }

    // Save new budget value to SharedPreferences
    private fun setBudget(amount: Int) {
        prefs.edit().putInt("monthly_budget", amount).apply()
    }
}
