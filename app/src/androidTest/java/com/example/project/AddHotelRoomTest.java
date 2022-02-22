package com.example.project;

import androidx.annotation.ContentView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


//TestCase Scenario - UI Test
@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddHotelRoomTest {

    @Rule
    //public ActivityScenarioRule<AddHotelRoom> room = new ActivityScenarioRule<AddHotelRoom>(AddHotelRoom.class);
    public ActivityTestRule<AddHotelRoom> room = new ActivityTestRule<>(AddHotelRoom.class);

    @Test
    public void addHotelRoom(){
        onView(withText("Data added Successfully"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

}

