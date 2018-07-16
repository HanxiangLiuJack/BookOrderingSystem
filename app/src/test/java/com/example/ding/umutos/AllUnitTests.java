package com.example.ding.umutos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.ding.umutos.objects.*;
import com.example.ding.umutos.business.unittests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAccountsTest.class,
        AccessBooksTest.class,
        AccessOrderTest.class,
        BookTest.class,
        AccountTest.class,
        OrderTest.class,
        BookSorterTest.class,
        BookValidatorTest.class,
        AccountValidatorTest.class,
        OrderValidatorTest.class,
        RateCalculaterTest.class,
        AccessShoppingCartTest.class,
        AccessWishListTest.class
})

public class AllUnitTests {

}
