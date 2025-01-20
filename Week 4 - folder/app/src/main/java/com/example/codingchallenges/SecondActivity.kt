package com.example.codingchallenges

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ITEM = "com.example.week4.extra.ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.button_cheese).setOnClickListener { returnItem("Cheese") }
        findViewById<View>(R.id.button_rice).setOnClickListener { returnItem("Rice") }
        findViewById<View>(R.id.button_apples).setOnClickListener { returnItem("Apples") }
    }

    private fun returnItem(item: String) {
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_ITEM, item)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}