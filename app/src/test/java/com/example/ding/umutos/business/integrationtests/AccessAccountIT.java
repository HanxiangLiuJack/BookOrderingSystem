package com.example.ding.umutos.business.integrationtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.utils.TestUtils;

import static junit.framework.Assert.assertNotNull;

import static junit.framework.Assert.assertTrue;

public class AccessAccountIT {
    private AccessAccounts accessAccounts;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.accessAccounts = new AccessAccounts();
    }

    @After
    public void tearDown()
    {
        TestUtils.delete();
        accessAccounts = null;
    }

    @Test
    public void testNullAccountAccess() {
        System.out.println("\nStarting test null account access.\n");
        assertNotNull(accessAccounts);
        System.out.println("\nFinished test null account access.\n");
    }

    @Test
    public void testGetAccounts() {
        System.out.println("\nStart testing get account.\n");
        final List<Account> accounts;
        accounts = accessAccounts.getAccounts();
        assertNotNull(accounts);
        assertTrue(accounts.size() == 6);
        System.out.println("\nfinished testing get account.\n");
    }

    @Test
    public void testGetAccountByName() {
        System.out.println("\nStart testing GetAccountByID.\n");

        final Account account;

        account = accessAccounts.getAccountByUserName("Tianhua Xu");
        assertNotNull(account);

        System.out.println("\nEnd testing GetAccountByID.\n");
    }

    @Test
    public void testInsertAccount()
    {
        System.out.println("\nStart testing testInsertAccount.\n");
        final Account newAccount = new Account("frank","1234");
        assertTrue(accessAccounts.insertAccount(newAccount));
        assertTrue(accessAccounts.getAccounts().size() == 7);
        System.out.println("\nEnd testing testInsertAccount.\n");
    }

    @Test
    public void testGetAccountRate()
    {
        System.out.println("\nStart testing testGetAccountRate.\n");
        assertNotNull(accessAccounts.getAccountRate("Tianhua Xu",accessAccounts.getAccounts()));
        System.out.println("\nEnd testing testGetAccountRate.\n");
    }

    @Test
    public void testRateUser()
    {
        System.out.println("\nStart testing testRateUser.\n");
        accessAccounts.RateUser("Tianhua Xu", 4.4);
        assertTrue(accessAccounts.getAccountByUserName("Tianhua Xu").getRatedPerson() == 4);
        System.out.println("\nEnd testing testRateUser.\n");
    }


}
