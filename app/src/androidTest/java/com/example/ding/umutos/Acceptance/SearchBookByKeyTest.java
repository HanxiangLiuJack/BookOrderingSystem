//User Story: As a user, i want to be able to search book by key words ( Tested )
package com.example.ding.umutos.Acceptance;


import com.example.ding.umutos.presentation.HomeActivity;
import com.example.ding.umutos.R;
import com.example.ding.umutos.presentation.LoginActivity;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
public class SearchBookByKeyTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setup()
    {
        onView(withId(R.id.loginUserName)).perform(typeText("Xiao Peng"));
        closeSoftKeyboard();

        onView(withId(R.id.loginPassword)).perform(typeText("66666666"));
        closeSoftKeyboard();
    }

    @Test
    public void createAccountAndLogin()
    {


        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.buttonLoginAsCustomer)).perform(click());

        onView(withId(R.id.searchByKeyword)).perform(typeText("Guilty Wife"));
        closeSoftKeyboard();


    }

}
