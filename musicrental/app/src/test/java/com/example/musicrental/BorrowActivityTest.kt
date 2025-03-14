package com.example.musicrental

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)  // Skip manifest loading to avoid errors
class BorrowActivityTest {

    private lateinit var activity: BorrowActivity

    @Before
    fun setUp() {
        // Use Robolectric to manually build and launch the activity
        activity = Robolectric.buildActivity(BorrowActivity::class.java).create().start().resume().get()
    }

    @After
    fun tearDown() {
        // Perform cleanup after the test
        activity.finish()
    }

    @Test
    fun testBorrowButtonDisabledOnEmptyName() {
        val nameInput = activity.findViewById<EditText>(R.id.renterName)
        val saveButton = activity.findViewById<Button>(R.id.saveButton)

        nameInput.setText("")  // Clear input field
        assertFalse(saveButton.isEnabled)  // Verify that the button is disabled
    }

    @Test
    fun testBorrowingWithValidName() {
        val nameInput = activity.findViewById<EditText>(R.id.renterName)
        val saveButton = activity.findViewById<Button>(R.id.saveButton)

        nameInput.setText("John Doe")
        assertTrue(saveButton.isEnabled)  // Verify that the button is enabled
    }

    @Test
    fun testCancelResetsCredits() {
        val cancelButton = activity.findViewById<Button>(R.id.cancelButton)
        val creditText = activity.findViewById<TextView>(R.id.creditText)

        cancelButton.performClick()
        assertEquals("Credits: 100", creditText.text.toString())  // Verify that credits are reset
    }
}
