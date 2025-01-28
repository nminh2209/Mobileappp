package com.example.climbingmoutainapp

import android.widget.TextView
import android.widget.Button
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class MainActivityTest {
    @Test
    fun testScoring() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()

        val scoreText = activity.findViewById<TextView>(R.id.scoreText)
        val climbButton = activity.findViewById<Button>(R.id.climbButton)
        val fallButton = activity.findViewById<Button>(R.id.fallButton)

        // Log initial state
        println("Initial score: ${scoreText.text}")
        assertNotNull("Score TextView should not be null", scoreText)
        assertNotNull("Climb Button should not be null", climbButton)
        assertNotNull("Fall Button should not be null", fallButton)

        // Test initial state
        assertEquals("Initial score should be 0", "0", scoreText.text.toString())

        // Test climbing
        climbButton.performClick()
        println("Score after climb: ${scoreText.text}")
        assertEquals("Score should be 1 after climbing", "1", scoreText.text.toString())

        // Test falling
        fallButton.performClick()
        println("Score after fall: ${scoreText.text}")
        assertEquals("Score should be 0 after falling", "0", scoreText.text.toString())
    }
}