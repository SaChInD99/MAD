package com.example.project;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

//Card Details Test 
@LargeTest
@RunWith(AndroidJUnit4.class)
public class CheckBill {

    private String cardnum;
    private String seqnum;
    private String exdate;
    private String phnnum;
    private String cardname;

    @Rule
    public ActivityScenarioRule<cardRet> cardRule = new ActivityScenarioRule<cardRet>(cardRet.class);

    @Before
    public void setUp() throws Exception{
        cardnum = "123456789100";
        seqnum = "0000";
        exdate = "2222";
        cardname = "ceylon";
        phnnum = "0772878751";
    }

    @Test
    public void test_login(){
        onView(withId(R.id.cardNumR))
                .perform(typeText(cardnum), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.seqNumR))
                .perform(typeText(seqnum), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.exDateR))
                .perform(typeText(exdate), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.crdNameR))
                .perform(typeText(cardname), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.phnNumR))
                .perform(typeText(phnnum), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btndelete)).perform(click());
    }

    @After
    public void tearDown(){
        cardnum = null;
        seqnum = null;
        exdate = null;
        cardname = null;
        phnnum = null;

    }
}
