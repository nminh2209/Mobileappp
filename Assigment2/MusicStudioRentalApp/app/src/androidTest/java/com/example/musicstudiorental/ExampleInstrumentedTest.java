package com.example.musicstudiorental;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Check if the app context is correct
        String packageName = activityRule.getActivity().getPackageName();
        assertEquals("com.example.musicstudiorental", packageName);
    }

    @Test
    public void testNextButton() {
        // Click on the "next" button and check if the next item is displayed
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.item_name)).check(matches(withText("Expected Item Name")));
    }
}