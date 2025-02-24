package com.example.week7

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var lsStudents: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val strList = arrayOf("Nguyen A", "Nguyen B", "Nguyen C", "Nguyen D")
        lsStudents = findViewById(R.id.lsStudents)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strList)
        lsStudents.adapter = adapter

        lsStudents.setOnItemClickListener { _, _, position, _ ->
            val element = adapter.getItem(position)
            Toast.makeText(this, element, Toast.LENGTH_LONG).show()
        }
    }
}
