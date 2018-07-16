package com.example.ding.umutos;

import com.example.ding.umutos.Acceptance.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({


        OrderRateWishTest.class,
        ShoppingCartTest.class,
        AddBookTest.class,
        CreateAccountTest.class,
        DeleteBookTest.class,
        EditBookTest.class,
        LoginWithBuyerOrSellerModeTest.class,
        SearchBookByCategoryTest.class,
        SearchBookByKeyTest.class,
        SortBookByPriceTest.class,

})

public class AllAcceptanceTest {

}
