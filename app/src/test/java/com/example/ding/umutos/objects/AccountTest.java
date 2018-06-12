package com.example.ding.umutos.objects;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class AccountTest {
    @Test
    public void testOrder()
    {
       Account account;
       account = new Account(12345,"John");

       System.out.println("Testing account");

       assertNotNull(account);
       assertTrue(12345 == account.getUserID());
       assertTrue("John".equals(account.getUserName()));

       account.setUserName("Amy");
       assertTrue("Amy".equals(account.getUserName()));
       
       System.out.println("Account Tests done");
    }
}
