package com.example.week4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private val LOG_TAG = SecondActivity::class.java.simpleName
    private lateinit var mReply: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mReply = findViewById(R.id.editText_second)
    }

    fun returnReply(view: View?) {
        val reply: String = mReply.text.toString()
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK, replyIntent)
        Log.d(LOG_TAG, "End SecondActivity")
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.week4.extra.REPLY"
    }
}