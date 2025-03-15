package com.example.musicrental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class BorrowActivity : AppCompatActivity() {
    private lateinit var instrument: Instrument  // The instrument being borrowed
    private var userCredits = 100  // User's available credits for renting
    private var originalCredits = 0  // Stores the initial credit before any deduction
    private var itemPrice = 0  // Stores the price of the selected instrument

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow)

        // Apply padding to the layout for better UI spacing
        findViewById<ConstraintLayout>(R.id.borrowLayout).apply {
            setPadding(50, 200, 50, 50)
        }

        // Retrieve the instrument object from the intent (Parcelable)
        instrument = intent.getParcelableExtra("instrument") ?: return
        userCredits = intent.getIntExtra("credits", 100)  // Get user's current credits from intent
        originalCredits = userCredits  // Store original credits to allow cancel restoration
        itemPrice = instrument.price  // Get instrument price from the object

        // Display user's available credits
        val creditTextView = findViewById<TextView>(R.id.creditText)
        creditTextView.text = "Credits: $userCredits"

        // Set instrument name and image
        findViewById<TextView>(R.id.borrowName).text = instrument.name
        findViewById<ImageView>(R.id.borrowImage).setImageResource(instrument.imageResId)

        // UI elements for renter input and action buttons
        val renterName = findViewById<EditText>(R.id.renterName)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Initially disable the save button until user enters a name
        saveButton.isEnabled = false

        // Listen for text changes in renter name input field
        renterName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Enable save button only if input is not empty
                saveButton.isEnabled = !s.isNullOrBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Handle borrow action when the save button is clicked
        saveButton.setOnClickListener {
            val renter = renterName.text.toString()

            // Ensure a valid renter name, available stock, and sufficient credits
            if (renter.isNotEmpty() && instrument.stock > 0 && userCredits >= itemPrice) {
                instrument.stock--  // Reduce stock by 1
                instrument.rentedBy = renter  // Assign renter's name
                userCredits -= itemPrice  // Deduct price from user credits
                creditTextView.text = "Credits: $userCredits"  // Update credit display

                // Apply fade animation to credit update
                applyCreditAnimation()

                // Prepare intent to return updated instrument and credits
                val resultIntent = Intent()
                resultIntent.putExtra("updatedInstrument", instrument)
                resultIntent.putExtra("credits", userCredits)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()  // Close the activity after successful rental
            } else {
                // Show error message if name is empty, stock is unavailable, or insufficient credits
                Snackbar.make(it, "Insufficient credits or empty name!", Snackbar.LENGTH_LONG).show()
            }
        }

        // Handle cancel action when the cancel button is clicked
        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            userCredits = originalCredits  // Restore original credits
            creditTextView.text = "Credits: $userCredits"  // Update credit display

            // Prepare intent to return credits without making a rental
            val resultIntent = Intent()
            resultIntent.putExtra("credits", userCredits)
            setResult(Activity.RESULT_OK, resultIntent)

            // Show cancellation message and close the activity
            Snackbar.make(it, "Rental cancelled", Snackbar.LENGTH_LONG).show()
            finish()
        }
    }

    // Function to animate credit text when updated
    private fun applyCreditAnimation() {
        val creditTextView = findViewById<TextView>(R.id.creditText)

        val fadeOut = AlphaAnimation(1.0f, 0.0f)  // Fade out effect
        fadeOut.duration = 300

        val fadeIn = AlphaAnimation(0.0f, 1.0f)  // Fade in effect
        fadeIn.duration = 300

        creditTextView.startAnimation(fadeOut)  // Start fade out animation
        creditTextView.text = "Credits: $userCredits"  // Update text
        creditTextView.startAnimation(fadeIn)  // Start fade in animation
    }
}
