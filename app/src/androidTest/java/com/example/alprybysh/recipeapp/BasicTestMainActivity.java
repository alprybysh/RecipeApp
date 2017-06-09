package com.example.alprybysh.recipeapp;

import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by aprybysh on 6/8/17.
 */

@RunWith(AndroidJUnit4.class)
public class BasicTestMainActivity {

    public static final String DESCRIP = "Recipe Introduction";
    public static final String DESCRIPSTEP1 = "1. Preheat";

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new
            ActivityTestRule<>(MainActivity.class);

    @Test
    public void cardIsPresent(){

       onView(withId(R.id.my_recycler_view)).check(matches(isDisplayed()));
    }


    @Test
    public void clickGridViewItemOpensRecipe (){
        onView(withId(R.id.my_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ingredients_view)).check(matches(isDisplayed()));

    }

    @Test
    public void openDescriptionRecipe() {
        onView(withId(R.id.my_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.my_recycler_view_fragment)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.description)).check(matches(withText(DESCRIP)));
    }

    @Test
    public void clickonNextButton() {
        onView(withId(R.id.my_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.my_recycler_view_fragment)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.description)).check(matches(withText(startsWith(DESCRIPSTEP1))));

    }
}


