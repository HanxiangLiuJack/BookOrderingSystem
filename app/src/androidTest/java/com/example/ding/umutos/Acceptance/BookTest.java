package com.example.ding.umutos;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.presentation.HomeActivity;
import com.example.ding.umutos.presentation.RegisterActivity;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

public class BookTest
{
    private String userName = "Zapp";
    private String userPsw = "Zapp123";
   @Rule
   public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

   @Before
   public void setup()
   {
       Service.getAccountPersistence().deleteAccount(new Account(userName,userPsw));
   }
   @After
    public void tearDown()
    {
        Service.getAccountPersistence().deleteAccount(new Account(userName,userPsw));
    }

   @Test
   public void createAccountAndLogin()
   {

       //ViewInteraction appCompatButton3 = onView(allOf(withId(R.id.btn_signup), withText("Create Account"), childAtPosition(childAtPosition(withClassName(is("android.support.constraint.ConstraintLayout")), 0), 3),isDisplayed()));

       onView(withId(R.id.buttonRegister)).perform(click());
       onView(withId(R.id.registerName)).perform(typeText(userName));
       onView(withId(R.id.registerPsw)).perform(typeText(userPsw));
       closeSoftKeyboard();
       onView(withId(R.id.registerSubmit)).perform(click());
       onView(withId(R.id.buttonLoginOut)).perform(click());

   }



}
