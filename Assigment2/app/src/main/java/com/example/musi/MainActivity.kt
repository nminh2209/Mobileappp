package com.example.musi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musi.models.RentalItem

class MainActivity : AppCompatActivity() {
    private lateinit var itemName: TextView
    private lateinit var itemRating: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemImage: ImageView
    private lateinit var nextButton: Button
    private lateinit var borrowButton: Button

    private lateinit var rentalItems: MutableList<RentalItem>
    private var currentIndex = 0

    private var username: String? = null
    private var credits: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemName = findViewById(R.id.item_name)
        itemRating = findViewById(R.id.item_rating)
        itemPrice = findViewById(R.id.item_price)
        itemImage = findViewById(R.id.item_image)
        nextButton = findViewById(R.id.next_button)
        borrowButton = findViewById(R.id.borrow_button)

        rentalItems = mutableListOf(
            RentalItem("Guitar", 4.0f, "Acoustic", 10.0f),
            RentalItem("Drum Set", 5.0f, "Electronic", 15.0f),
            RentalItem("Keyboard", 3.0f, "Synth", 12.0f),
            RentalItem("Microphone", 4.0f, "Dynamic", 8.0f)
        )

        username = intent.getStringExtra("username")
        credits = intent.getIntExtra("credits", 0)

        displayCurrentItem()

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % rentalItems.size
            displayCurrentItem()
        }

        borrowButton.setOnClickListener {
            val selectedItem = rentalItems[currentIndex]
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("selectedItem", selectedItem)
            startActivityForResult(intent, 1)
        }
    }

    private fun displayCurrentItem() {
        val currentItem = rentalItems[currentIndex]
        itemName.text = currentItem.name
        itemRating.text = currentItem.rating.toString()
        itemPrice.text = "${currentItem.pricePerMonth} credits"
        // Assume itemImage is set based on the item name or some resource mapping
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("credits", credits)
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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