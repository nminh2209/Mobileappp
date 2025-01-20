package com.example.codingchallenges

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var itemTextViews: List<TextView>
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemTextViews = listOf(
            findViewById(R.id.item1),
            findViewById(R.id.item2),
            // ... add all TextView references
            findViewById(R.id.item10)
        )

        findViewById<Button>(R.id.add_item_button).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        if (savedInstanceState != null) {
            for (i in itemTextViews.indices) {
                itemTextViews[i].text = savedInstanceState.getString("item$i", "")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        for (i in itemTextViews.indices) {
            outState.putString("item$i", itemTextViews[i].text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val item = data?.getStringExtra(SecondActivity.EXTRA_ITEM)
            item?.let {
                for (textView in itemTextViews) {
                    if (textView.text.isEmpty()) {
                        textView.text = it
                        break
                    }
                }
            }
        }
    }
}