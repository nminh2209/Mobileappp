package com.example.climbingmoutainapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.climbingmoutainapp.databinding.ActivityMainBinding
import android.widget.TextView
import android.widget.Button
import android.graphics.Color
import android.util.Log


class MainActivity : AppCompatActivity() {
    private var currentScore = 0
    private var currentHold = 0
    private var hasFallen = false
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        restoreState(savedInstanceState)
        setupButtons()
        updateScoreDisplay()

        Log.d(TAG, "App initialized with score: $currentScore")
    }


    private fun initializeViews() {
        scoreText = findViewById(R.id.scoreText)
        climbButton = findViewById(R.id.climbButton)
        fallButton = findViewById(R.id.fallButton)
        resetButton = findViewById(R.id.resetButton)
    }

    private fun setupButtons() {
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

    private fun updateScoreDisplay() {
        scoreText.text = currentScore.toString()
        scoreText.setTextColor(when (currentHold) {
            in 1..3 -> Color.BLUE
            in 4..6 -> Color.GREEN
            in 7..9 -> Color.RED
            else -> Color.BLACK
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SCORE, currentScore)
        outState.putInt(STATE_HOLD, currentHold)
        outState.putBoolean(STATE_FALLEN, hasFallen)
        Log.d(TAG, "State saved")
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            currentScore = it.getInt(STATE_SCORE)
            currentHold = it.getInt(STATE_HOLD)
            hasFallen = it.getBoolean(STATE_FALLEN)
            Log.d(TAG, "State restored")
        }
    }
}

