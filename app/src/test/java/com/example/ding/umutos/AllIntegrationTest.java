package com.example.ding.umutos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.ding.umutos.business.integrationtests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAccountIT.class,
        AccessOrderIT.class,
        AccessWishListIT.class,
        AccessBooksIT.class,
        AccessShoppingCartIT.class
})

public class AllIntegrationTest {
}
