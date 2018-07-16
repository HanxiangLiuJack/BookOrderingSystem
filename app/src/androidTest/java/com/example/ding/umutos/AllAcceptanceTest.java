package com.example.ding.umutos;

import com.example.ding.umutos.Acceptance.AddAndDeleteWishTest;
import com.example.ding.umutos.Acceptance.AddBookTest;
import com.example.ding.umutos.Acceptance.CreateAccountTest;
import com.example.ding.umutos.Acceptance.DeleteBookTest;
import com.example.ding.umutos.Acceptance.EditBookTest;
import com.example.ding.umutos.Acceptance.LoginWithBuyerOrSellerModeTest;
import com.example.ding.umutos.Acceptance.OrderBookAndRateSellerTest;
import com.example.ding.umutos.Acceptance.SearchBookByCategoryTest;
import com.example.ding.umutos.Acceptance.SearchBookByKeyTest;
import com.example.ding.umutos.Acceptance.SortBookByPriceTest;
import com.example.ding.umutos.Acceptance.ViewOrderAndWishListTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddAndDeleteWishTest.class,
        AddBookTest.class,
        CreateAccountTest.class,
        DeleteBookTest.class,
        EditBookTest.class,
        LoginWithBuyerOrSellerModeTest.class,
        OrderBookAndRateSellerTest.class,
        SearchBookByCategoryTest.class,
        SearchBookByKeyTest.class,
        SortBookByPriceTest.class,
        ViewOrderAndWishListTest.class
})

public class AllAcceptanceTest {

}
