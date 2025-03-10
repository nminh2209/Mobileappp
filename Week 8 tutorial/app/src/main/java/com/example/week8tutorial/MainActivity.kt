package com.example.week8tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Read file from res/raw
        val inputStream = resources.openRawResource(R.raw.data)
        val lines = inputStream.bufferedReader().readLines()

        // Log output (optional)
        lines.forEach { Log.d("FileData", it) }

        // Set adapter
        recyclerView.adapter = MyAdapter(lines)
    }
}
