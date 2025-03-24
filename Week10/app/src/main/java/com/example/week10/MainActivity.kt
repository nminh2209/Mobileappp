package com.example.week10

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var studentList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        val editTextName = findViewById<EditText>(R.id.editTextStudentName)
        val buttonInsert = findViewById<Button>(R.id.buttonInsertStudent)
        val listView = findViewById<ListView>(R.id.listViewStudents)

        // ✅ Load existing students
        studentList = dbHelper.getAllStudents().map { "${it.first}: ${it.second}" }.toMutableList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList)
        listView.adapter = adapter

        // ✅ Handle Insert Button Click
        buttonInsert.setOnClickListener {
            val name = editTextName.text.toString().trim()
            if (name.isNotEmpty()) {
                val id = (dbHelper.getAllStudents().size + 1)  // Simple auto-increment ID
                if (dbHelper.insertStudent(id, name)) {
                    studentList.add("$id: $name")  // Update UI
                    adapter.notifyDataSetChanged()
                    editTextName.text.clear()
                    Toast.makeText(this, "Student Added!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error inserting student", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter a name!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

// ✅ DBHelper class
class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertStudent(id: Int, name: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_ID, id)
            put(COL_NAME, name)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result != -1L
    }

    fun getAllStudents(): List<Pair<Int, String>> {
        val students = mutableListOf<Pair<Int, String>>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            students.add(Pair(id, name))
        }
        cursor.close()
        db.close()
        return students
    }

    companion object {
        const val DATABASE_NAME = "UniManagement.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Students"
        const val COL_ID = "id"
        const val COL_NAME = "name"
    }
}
