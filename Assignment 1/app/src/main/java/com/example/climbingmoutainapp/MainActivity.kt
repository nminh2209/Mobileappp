package com.example.climbingmoutainapp

import android.content.res.Configuration

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var currentScore = 0 //Tracks the user's score.
    private var currentHold = 0  //Tracks the hold level.
    private var hasFallen = false //Boolean flag to prevent additional actions after a fall.
    private lateinit var scoreText: TextView
    private lateinit var climbButton: Button
    private lateinit var fallButton: Button
    private lateinit var resetButton: Button

    companion object {
        private const val STATE_SCORE = "currentScore"
        private const val STATE_HOLD = "currentHold"
        private const val STATE_FALLEN = "hasFallen"
        private const val TAG = "ClimbingScoreApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        val langEnglish = findViewById<Button>(R.id.langEnglish)
        val langJapanese = findViewById<Button>(R.id.langJapanese)
        val langVietnamese = findViewById<Button>(R.id.langVietnamese)

        langEnglish.setOnClickListener { v: View? -> setLocale("en") }
        langJapanese.setOnClickListener { v: View? -> setLocale("ja") }
        langVietnamese.setOnClickListener { v: View? -> setLocale("vi") }

        initializeViews()
        restoreState(savedInstanceState)
        setupButtons()
        updateScoreDisplay()

        Log.d(TAG, "App initialized with score: $currentScore")
    }

    private fun setLocale(languageCode: String) { // Dynamically updates the app language.
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }


    private fun initializeViews() { // Initializes UI components.
        scoreText = findViewById(R.id.scoreText)
        climbButton = findViewById(R.id.climbButton)
        fallButton = findViewById(R.id.fallButton)
        resetButton = findViewById(R.id.resetButton)
    }

    private fun setupButtons() {  // Defines button actions for climbing, falling, and resetting.
        climbButton.setOnClickListener {
            if (!hasFallen && currentHold < 9) {
                currentHold++
                currentScore += when (currentHold) {
                    in 1..3 -> 1  // Blue zone
                    in 4..6 -> 2  // Green zone
                    in 7..9 -> 3  // Red zone
                    else -> 0
                }
                Log.d(TAG, "Climbed to hold $currentHold, score: $currentScore")
                updateScoreDisplay()
            }
        }

        fallButton.setOnClickListener {
            if (currentHold > 0 && currentHold < 9 && !hasFallen) {
                currentScore = maxOf(0, currentScore - 3)
                hasFallen = true
                Log.d(TAG, "Fall recorded, new score: $currentScore")
                updateScoreDisplay()
            }
        }

        resetButton.setOnClickListener {
            currentScore = 0
            currentHold = 0
            hasFallen = false
            Log.d(TAG, "Score reset")
            updateScoreDisplay()
        }
    }

    private fun updateScoreDisplay() {  // Updates UI elements based on user progress.
        scoreText.text = currentScore.toString()
        scoreText.setTextColor(when (currentHold) {
            in 1..3 -> Color.BLUE
            in 4..6 -> Color.GREEN
            in 7..9 -> Color.RED
            else -> Color.BLACK
        })
    }

    override fun onSaveInstanceState(outState: Bundle) { //stores the game state in a Bundle before the activity is paused or destroyed.
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SCORE, currentScore)
        outState.putInt(STATE_HOLD, currentHold)
        outState.putBoolean(STATE_FALLEN, hasFallen)
        Log.d(TAG, "State saved")
    }

    private fun restoreState(savedInstanceState: Bundle?) { //•	restoreState() retrieves the stored data and reinstates the previous game state.
        savedInstanceState?.let {
            currentScore = it.getInt(STATE_SCORE)
            currentHold = it.getInt(STATE_HOLD)
            hasFallen = it.getBoolean(STATE_FALLEN)
            Log.d(TAG, "State restored")
        }
    }
}

