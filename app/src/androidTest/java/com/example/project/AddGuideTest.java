package com.example.project;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

//TestCase Scenario - UI Test  
@LargeTest
@RunWith(AndroidJUnit4.class)

public class AddGuideTest {

    @Rule
    //public ActivityScenarioRule<AddHotelRoom> room = new ActivityScenarioRule<AddHotelRoom>(AddHotelRoom.class);
    public ActivityTestRule<adddata> room = new ActivityTestRule<>(adddata.class);

    @Test
    public void addGuide(){
        onView(withText("Inserted Successfully"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }
}
