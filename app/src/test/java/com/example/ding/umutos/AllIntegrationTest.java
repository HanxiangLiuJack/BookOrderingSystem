package com.example.ding.umutos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.ding.umutos.objects.*;
import com.example.ding.umutos.business.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAccountIT.class,
        AccessOrderIT.class,
        AccessWishListIT.class,
        //AccessBookIT.class,
        AccessShoppingCartIT.class
})

public class AllIntegrationTest {
}
