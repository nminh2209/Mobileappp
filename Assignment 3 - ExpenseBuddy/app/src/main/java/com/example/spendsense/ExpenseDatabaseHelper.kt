package com.example.spendsense

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Helper function to format date strings from "DDMMYYYY" to "DD/MM/YYYY"
fun formatDate(inputDate: String): String {
    // Ensure the input is exactly 8 characters long
    if (inputDate.length == 8) {
        val day = inputDate.substring(0, 2)
        val month = inputDate.substring(2, 4)
        val year = inputDate.substring(4, 8)
        return "$day/$month/$year"
    }
    return inputDate // Return the original if not in expected format
}

// SQLiteOpenHelper class to manage database creation and version management
class ExpenseDatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database and table metadata
        private const val DATABASE_NAME = "expense.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allexpenses"

        // Column names
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "amount"
        private const val COLUMN_CONTENT = "content"
        private const val COLUMN_DATE = "date"
    }

    // Called when the database is first created
    override fun onCreate(db: SQLiteDatabase?) {
        // SQL query to create the table with columns
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_TITLE INTEGER,
                $COLUMN_CONTENT TEXT,
                $COLUMN_DATE TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    // Called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop the old table and recreate it
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    // Inserts a new expense into the database
    fun insertExpense(expense: Expense) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, expense.amount)
            put(COLUMN_CONTENT, expense.content)
            put(COLUMN_DATE, expense.date)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Retrieves all expenses from the database and returns them as a list
    fun getAllExpenses(): List<Expense> {
        val expenseList = mutableListOf<Expense>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        // Loop through each row and build an Expense object
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))

            val expense = Expense(id, title, content, date)
            expenseList.add(expense)
        }

        cursor.close()
        db.close()
        return expenseList
    }

    // Updates an existing expense record
    fun updateExpense(expense: Expense) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, expense.amount)
            put(COLUMN_CONTENT, expense.content)
            put(COLUMN_DATE, expense.date)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(expense.id.toString())

        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    // Fetches a single expense by its ID
    fun getExpenseByID(expenseId: Int): Expense {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $expenseId"
        val cursor = db.rawQuery(query, null)

        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))

        cursor.close()
        db.close()
        return Expense(id, title, content, date)
    }

    // Deletes an expense by its ID
    fun deleteExpense(expenseId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(expenseId.toString())

        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }

    fun getTotalExpenses(): Int {
        val db = readableDatabase
        val query = "SELECT SUM(amount) as Total FROM allexpenses"
        val cursor = db.rawQuery(query, null)
        var total = 0
        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndexOrThrow("Total"))
        }
        cursor.close()
        //db.close()
        return total
    }

}
