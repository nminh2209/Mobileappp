package com.example.musi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musi.models.RentalItem

class ItemDetailActivity : AppCompatActivity() {
    private lateinit var itemName: TextView
    private lateinit var itemRating: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemAttribute: TextView
    private lateinit var borrowButton: Button
    private lateinit var cancelButton: Button
    private var rentalItem: RentalItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        itemName = findViewById(R.id.item_name)
        itemRating = findViewById(R.id.item_rating)
        itemPrice = findViewById(R.id.item_price)
        itemAttribute = findViewById(R.id.item_attribute)
        borrowButton = findViewById(R.id.borrow_button)
        cancelButton = findViewById(R.id.cancel_button)

        // Retrieve the RentalItem passed from MainActivity
        rentalItem = intent.getParcelableExtra("selectedItem")

        rentalItem?.let {
            displayItemDetails(it)
        }

        borrowButton.setOnClickListener {
            // Handle the borrow action
            Toast.makeText(this@ItemDetailActivity, "Item borrowed!", Toast.LENGTH_SHORT).show()
            // Optionally, you can finish the activity or navigate back
            finish()
        }

        cancelButton.setOnClickListener {
            // Handle the cancel action
            Toast.makeText(this@ItemDetailActivity, "Action cancelled.", Toast.LENGTH_SHORT).show()
            // Optionally, you can finish the activity or navigate back
            finish()
        }
    }

    private fun displayItemDetails(item: RentalItem) {
        itemName.text = item.name
        itemRating.text = item.rating.toString()
        itemPrice.text = "${item.pricePerMonth} credits"
        itemAttribute.text = item.multiChoiceAttribute
        // Set image resource if applicable
        // val itemImage: ImageView = findViewById(R.id.item_image)
        // itemImage.setImageResource(item.getImageResource())
    }
}