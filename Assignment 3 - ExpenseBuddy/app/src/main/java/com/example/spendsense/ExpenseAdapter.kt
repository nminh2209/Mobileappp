package com.example.spendsense

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Adapter class for managing and displaying a list of expenses in a RecyclerView
class ExpenseAdapter(
    private var expense: List<Expense>, // List of expense data
    context: Context // Context used to interact with the database
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    // Database helper to manage expense data
    private val db: ExpenseDatabaseHelper = ExpenseDatabaseHelper(context.applicationContext)

    // ViewHolder class that holds references to the UI components of each list item
    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    // Inflates the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    // Returns the number of items in the expense list
    override fun getItemCount(): Int = expense.size

    // Binds the data from the expense list to the corresponding views
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expense[position] // Get the expense at the current position

        // Display the amount if it’s a valid integer
        if (currentExpense.amount != null && currentExpense.amount is Int) {
            holder.titleTextView.text = currentExpense.amount.toString()
        } else {
            // Show a placeholder if amount is null or invalid
            holder.titleTextView.text = "Invalid Amount"
        }

        // Set the content and date text views
        holder.contentTextView.text = currentExpense.content
        holder.dateTextView.text = currentExpense.date

        // Set up click listener to launch the update activity
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateExpenseActivity::class.java).apply {
                putExtra("expense_id", currentExpense.id) // Pass the expense ID
            }
            holder.itemView.context.startActivity(intent)
        }

        // Set up click listener to delete the expense from the database
        holder.deleteButton.setOnClickListener {
            db.deleteExpense(currentExpense.id) // Delete from DB
            refreshData(db.getAllExpenses()) // Refresh the list
            Toast.makeText(holder.itemView.context, "Expense Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    // Updates the adapter with a new list of expenses and refreshes the view
    fun refreshData(newExpense: List<Expense>) {
        expense = newExpense
        notifyDataSetChanged()
    }
}
