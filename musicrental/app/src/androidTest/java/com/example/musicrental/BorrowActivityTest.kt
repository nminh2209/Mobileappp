package com.example.musicrental

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Add this annotation to register the test class properly
@RunWith(AndroidJUnit4::class)
class BorrowActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(BorrowActivity::class.java)

    @Test
    fun testBorrowButtonDisabledOnEmptyName() {
        // Ensure input field is empty
        onView(withId(R.id.renterName)).perform(clearText())
        // Check that the save button is disabled
        onView(withId(R.id.saveButton)).check(matches(isNotEnabled()))
    }

    @Test
    fun testBorrowingWithValidName() {
        // Enter a valid name and close the keyboard
        onView(withId(R.id.renterName)).perform(typeText("John Doe"), closeSoftKeyboard())
        // Click the save button
        onView(withId(R.id.saveButton)).perform(click())
    }

    @Test
    fun testCancelResetsCredits() {
        // Click cancel button
        onView(withId(R.id.cancelButton)).perform(click())
        // Check if credits reset correctly
        onView(withId(R.id.creditText)).check(matches(withText("Credits: 100")))
    }
}
