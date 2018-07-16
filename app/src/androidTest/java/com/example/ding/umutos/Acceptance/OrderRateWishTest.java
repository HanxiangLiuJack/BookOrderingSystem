
//User story: as a user, i want to be able to place an order(Tested)
//User story: as a user, i want to be able to confirm my order(Tested)
//User story: as a user, i want to be able to rate the seller(Tested)
//User story: as a user, i want to be able to view order history(Tested)
//User Story: As a user, i want to be able to  view order history ( Tested )
//User Story: As a user, i want to be able to view wish list ( Tested )
//User story: as a user, i want to be able to add book from wish List(Tested)
//User story: as a user, i want to be able to delete Book from wish list(Tested)
package com.example.ding.umutos.Acceptance;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.presentation.HomeActivity;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.ding.umutos.R;


import org.hamcrest.Matcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.Matchers.is;

import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class OrderRateWishTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);
    @Before
    public void setup()
    {
        onView(withId(R.id.loginUserName)).perform(typeText("Xiao Peng"));
        closeSoftKeyboard();

        onView(withId(R.id.loginPassword)).perform(typeText("66666666"));
        closeSoftKeyboard();

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.buttonLoginAsCustomer)).perform(click());

        closeSoftKeyboard();

    }

    @Test
    public void OrderHistoryTest() {

       testPlaceAnOrder();
       testCreateAndEditAnOrder();
       testConfirmAnOrder();
       testViewOrderHistory();
       testRateSeller();
       testAddWish();
       testDeleteWish();

    }

  
    private void testPlaceAnOrder()
    {
        onData(anything()).inAdapterView(withId(R.id.cusListView)).atPosition(0).perform(click());

        onView(withId(R.id.singleBookAddToCart)).perform(click());

        onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.viewShoppingCart)).perform(click());

        onView(withId(R.id.shoppingListDone)).perform(click());

        onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    }

    private void testCreateAndEditAnOrder()
    {
        onView(withId(R.id.editFirstName)).perform(typeText("abcd"));
        closeSoftKeyboard();

        onView(withId(R.id.editLastName)).perform(typeText("cccc"));
        closeSoftKeyboard();

        onView(withId(R.id.editPhoneNum)).perform(typeText("123456"));
        closeSoftKeyboard();

        onView(withId(R.id.editPostCode)).perform(typeText("R2M2J8"));
        closeSoftKeyboard();

        onView(withId(R.id.editAddressInfo)).perform(typeText("R2M2J8"));
        closeSoftKeyboard();

        onView(withId(R.id.editAdditionInfo)).perform(typeText("R2M2J8"));
        closeSoftKeyboard();

        onView(withId(R.id.addSubmit)).perform(click());



    }

    private void testConfirmAnOrder()
    {
        onView(withText("OK")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        closeSoftKeyboard();
    }

    private void testViewOrderHistory()
    {
        onView(withId(R.id.viewCustomerHistory)).perform(click());

    }

    private void testRateSeller()
    {
        onData(anything()).inAdapterView(withId(R.id.historyBookList)).atPosition(0).perform(click());

        onView(withId(R.id.editRatingBar)).perform(setRating(4.5f));

        onView(withId(R.id.rateSubmit)).perform(click());

        onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    }

    private void testAddWish()
    {
        onView(withId(R.id.historyBack)).perform(click());

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




    private static ViewAction setRating(final float rating) {
        if (rating % 0.5 != 0) {
            throw new IllegalArgumentException("Rating must be multiple of 0.5f");
        }

        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(RatingBar.class);
            }

            @Override
            public String getDescription() {
                return "Set rating on RatingBar in 0.5f increments";
            }

            @Override
            public void perform(UiController uiController, View view) {
                GeneralClickAction viewAction = new GeneralClickAction(
                        Tap.SINGLE,
                        new CoordinatesProvider() {
                            @Override
                            public float[] calculateCoordinates(View view) {
                                int[] locationOnScreen = new int[2];

                                view.getLocationOnScreen(locationOnScreen);
                                int screenX = locationOnScreen[0];
                                int screenY = locationOnScreen[1];
                                int numStars = ((RatingBar) view).getNumStars();
                                float widthPerStar = 1f * view.getWidth() / numStars;
                                float percent = rating / numStars;
                                float x = screenX + view.getWidth() * percent;
                                float y = screenY + view.getHeight() * 0.5f;
                                return new float[]{x - widthPerStar * 0.5f, y};
                            }
                        },
                        Press.FINGER,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                );
                viewAction.perform(uiController, view);
            }
        };
    }
}