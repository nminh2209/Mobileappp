package com.example.spendsense

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

class EditBudgetDialog(
    context: Context,
    currentBudget: Int,
    private val onSave: (Int) -> Unit
) {
    private val input = EditText(context).apply {
        hint = "Enter new budget"
        setText(currentBudget.toString())
        inputType = android.text.InputType.TYPE_CLASS_NUMBER
    }

    private val dialog = AlertDialog.Builder(context)
        .setTitle("Edit Your Budget")
        .setView(input)
        .setPositiveButton("Save") { _, _ ->
            val value = input.text.toString().toIntOrNull()
            if (value != null && value >= 0) {
                onSave(value)
            }
        }
        .setNegativeButton("Cancel", null)
        .create()

    fun show() = dialog.show()
}
