package com.example.ding.umutos;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.ding.umutos.objects.*;
import com.example.ding.umutos.business.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAccountsTest.class,
        AccessBooksTest.class,
        BookTest.class,
        AccountTest.class,
        BookSorterTest.class
})

public class AllTests {

}
