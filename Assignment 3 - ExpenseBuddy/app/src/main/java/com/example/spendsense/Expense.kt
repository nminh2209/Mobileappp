package com.example.spendsense

// Data class representing an expense item
data class Expense(
    val id: Int,        // Unique ID for the expense (used as primary key)
    val amount: Int,    // Amount spent (stored as integer)
    val content: String,// Description or reason for the expense
    val date: String    // Date of the expense in string format (e.g., "12/04/2025")
)
