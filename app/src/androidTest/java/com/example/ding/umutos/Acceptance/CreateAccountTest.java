package com.example.ding.umutos.Acceptance;



import com.example.ding.umutos.presentation.HomeActivity;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.ding.umutos.R;
import com.example.ding.umutos.presentation.LoginActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateAccountTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void createAccountAndLogin()
    {
        onView( withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.registerName)).perform(typeText("Zapp"));
        closeSoftKeyboard();

        onView(withId(R.id.registerPsw)).perform(typeText("Zapp123"));
        closeSoftKeyboard();

        onView(withId(R.id.registerSubmit)).perform(click());

        onView(withId(R.id.buttonLoginOut)).perform(click());
    }



}

