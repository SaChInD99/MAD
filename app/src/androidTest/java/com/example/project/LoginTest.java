package com.example.project;

import androidx.annotation.ContentView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


//Login Test  
@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private String email;
    private String password;

    @Rule
    public ActivityScenarioRule<Login_Activity> loginRule = new ActivityScenarioRule<Login_Activity>(Login_Activity.class);

    @Before
    public void setUp() throws Exception{
        email = "fakelogin@gmail.com";
        password = "fake123";
    }

    @Test
    public void test_login(){
        onView(withId(R.id.login_yourname))
                .perform(typeText(email), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_password))
                .perform(typeText(password), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Login_Btn)).perform(click());
    }

    @After
    public void tearDown(){
        email = null;
        password = null;

    }
}
