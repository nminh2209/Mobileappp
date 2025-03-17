package com.example.musicrental

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    // List of available instruments with name, image, rating, attributes, price, and stock.
    private val instruments = mutableListOf(
        Instrument("Acoustic Guitar", R.drawable.guitar_image, 4.5f, listOf("Wooden", "6-String"), 20, 5, null),
        Instrument("Piano", R.drawable.piano_image, 4.2f, listOf("88 Keys", "Grand"), 30, 3, null),
        Instrument("Drum Set", R.drawable.drum_image, 4.7f, listOf("Acoustic", "5-Piece"), 50, 2, null),
        Instrument("Electric Guitar", R.drawable.eguitar_image, 4.5f, listOf("Electric", "6-String"), 20, 5, null),
        Instrument("Electric Keyboard", R.drawable.epiano_image, 4.2f, listOf("88 Keys", "Digital"), 30, 3, null),
        Instrument("Violin", R.drawable.violin_image, 4.7f, listOf("Wooden", "4-String"), 50, 2, null)
    )

    private var currentIndex = 0 // Tracks the currently displayed instrument
    private var userCredits = 100 // Initial user credits
    private var itemPrice = 0 // Stores the current instrument price

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load theme preference before setting content view
        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isDarkMode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_main)

        // Theme toggle button
        findViewById<Button>(R.id.toggleThemeButton).setOnClickListener {
            val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
            val editor = sharedPreferences.edit()

            val newMode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.setDefaultNightMode(newMode)

            editor.putBoolean("isDarkMode", !isDarkMode)
            editor.apply()

            // 🔥 Prevent shifting UI elements
            window.decorView.post {
                window.decorView.requestLayout()
            }
        }


        // Adjusts padding dynamically to ensure a responsive layout
        findViewById<ConstraintLayout>(R.id.mainLayout).apply {
            setPadding(50, 200, 50, 50)
        }

        // Initialize UI with the first instrument
        updateUI()

        // Handles button click to show the next instrument in the list
        findViewById<Button>(R.id.nextButton).setOnClickListener {
            currentIndex = (currentIndex + 1) % instruments.size // Loops through the instrument list
            updateUI() // Refreshes UI with new instrument details
        }

        // Handles borrowing process by opening BorrowActivity with selected instrument
        findViewById<Button>(R.id.borrowButton).setOnClickListener {
            val intent = Intent(this, BorrowActivity::class.java)
            intent.putExtra("instrument", instruments[currentIndex]) // Pass selected instrument
            intent.putExtra("credits", userCredits) // Pass current user credits
            startActivityForResult(intent, 1) // Start activity and wait for result
        }
    }

    // Handles result from BorrowActivity when returning an instrument
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val updatedInstrument = data?.getParcelableExtra<Instrument>("updatedInstrument") // Get updated instrument
            userCredits = data?.getIntExtra("credits", userCredits) ?: userCredits // Update user credits
            updatedInstrument?.let { newInstrument ->
                instruments[currentIndex] = newInstrument // Update instrument list
                updateUI() // Refresh UI with new data
            }
        }
    }

    // Updates the UI with the current instrument's details
    private fun updateUI() {
        val instrument = instruments[currentIndex] // Get current instrument
        itemPrice = instrument.price // Set item price

        // Update instrument name
        findViewById<TextView>(R.id.instrumentName).text = instrument.name
        // Update instrument image
        findViewById<ImageView>(R.id.instrumentImage).setImageResource(instrument.imageResId)
        // Update instrument rating
        findViewById<RatingBar>(R.id.ratingBar).rating = instrument.rating
        // Update stock availability
        findViewById<TextView>(R.id.stockText).text = "Stock: ${instrument.stock} left"
        // Display who rented the instrument, or show "Available"
        findViewById<TextView>(R.id.rentedByText).text = "Rented by: ${instrument.rentedBy ?: "Available"}"

        // Update displayed credits required for the item
        val creditTextView = findViewById<TextView>(R.id.creditText)
        creditTextView.text = "Credits: $itemPrice"

        // Update chip group to display instrument attributes
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.removeAllViews() // Clear previous chips
        for (attr in instrument.attributes) {
            val chip = Chip(this)
            chip.text = attr
            chipGroup.addView(chip) // Add chip to the group
        }

        // Enable or disable borrow button based on credit and stock availability
        val borrowButton = findViewById<Button>(R.id.borrowButton)
        borrowButton.isEnabled = userCredits >= itemPrice && instrument.stock > 0
    }
}
