//User story: as a user, i want to be able to add book from wish List(Tested)
//User story: as a user, i want to be able to delete Book from wish list(Tested)

package com.example.ding.umutos.Acceptance;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.presentation.HomeActivity;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.ding.umutos.R;


import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
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
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.CoreMatchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddAndDeleteWishTest {
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
   public void TestAddAndDelete()
   {
      TestAddWish();
      testDeleteWish();
   }

    private void TestAddWish()
   {
       onView(withId(R.id.buttonLogin)).perform(click());

       onView(withId(R.id.buttonLoginAsCustomer)).perform(click());

       onView(withId(R.id.cusWishList)).perform(click());

       onView(withId(R.id.addWish)).perform(click());

       onView(withId(R.id.wishBookName)).perform(typeText("Wished Book"));
       closeSoftKeyboard();
       onView(withId(R.id.wishAuthorName)).perform(typeText("Test"));
       closeSoftKeyboard();

       onView(withId(R.id.addWishSubmit)).perform(click());

       onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());


   }

   private void testDeleteWish()
   {
       onData(anything()).inAdapterView(withId(R.id.wishList)).atPosition(0).perform(click());

       onView(withId(R.id.wishListRemove)).perform(click());

       onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
   }
}
