package com.example.ding.umutos.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AccountTest {

    private Account templateAccount;

    @Before
    public void setup()
    {
        templateAccount = new Account(12345, "John");
    }

    @After
    public void tearDown()
    {
        templateAccount = null;
    }

    @Test
    public void testNullAccount()
    {
        System.out.println("Testing Null account");
        assertNotNull(templateAccount);
        System.out.println("Null Account Tests done");
    }

    @Test
    public void testAccessorMethods()
    {
        System.out.println("Testing get methods");
        assertTrue(12345 == templateAccount.getUserID());
        assertTrue("John".equals(templateAccount.getUserName()));
        System.out.println("End Testing get methods");
    }

    @Test
    public void testMutatorMethod()
    {
        System.out.println("Testing set methods");
        templateAccount.setUserName("Amy");
        assertTrue("Amy".equals(templateAccount.getUserName()));
        System.out.println("End Testing set methods");
    }
}
