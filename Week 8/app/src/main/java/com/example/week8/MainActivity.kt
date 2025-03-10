package com.example.week8

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var thread1TimeTextView: TextView
    private lateinit var thread2TimeTextView: TextView
    private lateinit var startThreadsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread1TimeTextView = findViewById(R.id.thread1TimeTextView)
        thread2TimeTextView = findViewById(R.id.thread2TimeTextView)
        startThreadsButton = findViewById(R.id.startThreadsButton)

        // Start T1 and T2 in parallel on button click
        startThreadsButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val t1Time = async(Dispatchers.IO) { thread1() }
                val t2Time = async(Dispatchers.IO) { thread2() }

                // Wait for both to complete
                thread1TimeTextView.text = "T1 Execution Time: ${t1Time.await()} ms"
                thread2TimeTextView.text = "T2 Execution Time: ${t2Time.await()} ms"
            }
        }
    }

    // T1: Runs in background and returns execution time
    private suspend fun thread1(): Long {
        val startTime = System.currentTimeMillis()

        for (i in 0..1000) {
            Log.d("T1", "Thread 1 Running: $i")
        }

        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }

    // T2: Runs in background and returns execution time
    private suspend fun thread2(): Long {
        val startTime = System.currentTimeMillis()

        for (i in 0..1000) {
            Log.d("T2", "Thread 2 Running: $i")
        }

        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }
}
