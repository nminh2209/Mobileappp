package com.example.musicrental

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    private val instruments = mutableListOf(
        Instrument("Acoustic Guitar", R.drawable.guitar_image, 4.5f, listOf("Wooden", "6-String"), 20, 5, null),
        Instrument("Piano", R.drawable.piano_image, 4.2f, listOf("88 Keys", "Grand"), 30, 3, null),
        Instrument("Drum Set", R.drawable.drum_image, 4.7f, listOf("Acoustic", "5-Piece"), 50, 2, null),
        Instrument("Electric Guitar", R.drawable.eguitar_image, 4.5f, listOf("Electric", "6-String"), 20, 5, null),
        Instrument("Electric Keyboard", R.drawable.epiano_image, 4.2f, listOf("88 Keys", "Digital"), 30, 3, null),
        Instrument("Violin", R.drawable.violin_image, 4.7f, listOf("Wooden", "4-String"), 50, 2, null)
    )
    private var currentIndex = 0
    private var userCredits = 100
    private var itemPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ConstraintLayout>(R.id.mainLayout).apply {
            setPadding(50, 200, 50, 50)
        }

        updateUI()
        findViewById<Button>(R.id.nextButton).setOnClickListener {
            currentIndex = (currentIndex + 1) % instruments.size
            updateUI()
        }
        findViewById<Button>(R.id.borrowButton).setOnClickListener {
            val intent = Intent(this, BorrowActivity::class.java)
            intent.putExtra("instrument", instruments[currentIndex])
            intent.putExtra("credits", userCredits)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val updatedInstrument = data?.getParcelableExtra<Instrument>("updatedInstrument")
            userCredits = data?.getIntExtra("credits", userCredits) ?: userCredits
            updatedInstrument?.let { newInstrument ->
                instruments[currentIndex] = newInstrument
                updateUI()
            }
        }
    }

    private fun updateUI() {
        val instrument = instruments[currentIndex]
        itemPrice = instrument.price
        findViewById<TextView>(R.id.instrumentName).text = instrument.name
        findViewById<ImageView>(R.id.instrumentImage).setImageResource(instrument.imageResId)
        findViewById<RatingBar>(R.id.ratingBar).rating = instrument.rating
        findViewById<TextView>(R.id.stockText).text = "Stock: ${instrument.stock} left"
        findViewById<TextView>(R.id.rentedByText).text = "Rented by: ${instrument.rentedBy ?: "Available"}"
        val creditTextView = findViewById<TextView>(R.id.creditText)
        creditTextView.text = "Credits: $itemPrice"
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.removeAllViews()
        for (attr in instrument.attributes) {
            val chip = Chip(this)
            chip.text = attr
            chipGroup.addView(chip)
        }
        val borrowButton = findViewById<Button>(R.id.borrowButton)
        borrowButton.isEnabled = userCredits >= itemPrice && instrument.stock > 0
    }
}

