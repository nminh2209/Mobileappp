package com.example.musi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musi.models.RentalItem

class MainActivity : AppCompatActivity(), RentalItemAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var rentalItems: MutableList<RentalItem>
    private var username: String? = null
    private var credits: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        rentalItems = mutableListOf(
            RentalItem("Guitar", 4.0f, "Acoustic", 10.0f),
            RentalItem("Drum Set", 5.0f, "Electronic", 15.0f),
            RentalItem("Keyboard", 3.0f, "Synth", 12.0f),
            RentalItem("Microphone", 4.0f, "Dynamic", 8.0f)
        )

        username = intent.getStringExtra("username")
        credits = intent.getIntExtra("credits", 0)

        val adapter = RentalItemAdapter(this, rentalItems, this)
        recyclerView.adapter = adapter
    }

    override fun onBorrowClick(rentalItem: RentalItem) {
        val intent = Intent(this, ItemDetailActivity::class.java)
        intent.putExtra("selectedItem", rentalItem)
        startActivityForResult(intent, 1)
    }

    override fun onCancelClick(rentalItem: RentalItem) {
        Toast.makeText(this, "Action cancelled for ${rentalItem.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Booking cancelled.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}