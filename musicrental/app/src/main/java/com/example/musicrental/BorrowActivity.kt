package com.example.musicrental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class BorrowActivity : AppCompatActivity() {
    private lateinit var instrument: Instrument
    private var userCredits = 100
    private var originalCredits = 0
    private var itemPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow)

        findViewById<ConstraintLayout>(R.id.borrowLayout).apply {
            setPadding(50, 200, 50, 50)
        }

        instrument = intent.getParcelableExtra("instrument") ?: return
        userCredits = intent.getIntExtra("credits", 100)
        originalCredits = userCredits
        itemPrice = instrument.price

        val creditTextView = findViewById<TextView>(R.id.creditText)
        creditTextView.text = "Credits: $userCredits" // Set initial credit display

        findViewById<TextView>(R.id.borrowName).text = instrument.name
        findViewById<ImageView>(R.id.borrowImage).setImageResource(instrument.imageResId)

        val renterName = findViewById<EditText>(R.id.renterName)
        renterName.minHeight = 60

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val renter = renterName.text.toString()
            if (renter.isNotEmpty() && instrument.stock > 0 && userCredits >= itemPrice) {
                instrument.stock--
                instrument.rentedBy = renter
                userCredits -= itemPrice
                creditTextView.text = "Credits: $userCredits" // Update credits after borrowing
                applyCreditAnimation()
                val resultIntent = Intent()
                resultIntent.putExtra("updatedInstrument", instrument)
                resultIntent.putExtra("credits", userCredits)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Snackbar.make(it, "Insufficient credits or empty name!", Snackbar.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            userCredits = originalCredits
            creditTextView.text = "Credits: $userCredits" // Restore credits on cancel
            val resultIntent = Intent()
            resultIntent.putExtra("credits", userCredits)
            setResult(Activity.RESULT_OK, resultIntent)
            Snackbar.make(it, "Rental cancelled", Snackbar.LENGTH_LONG).show()
            finish()
        }
    }

    private fun applyCreditAnimation() {
        val creditTextView = findViewById<TextView>(R.id.creditText)
        val fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = 300
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 300
        creditTextView.startAnimation(fadeOut)
        creditTextView.text = "Credits: $userCredits"
        creditTextView.startAnimation(fadeIn)
    }
}
