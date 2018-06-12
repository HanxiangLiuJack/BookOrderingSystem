package com.example.ding.umutos.business;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.ding.umutos.objects.Account;


import static junit.framework.Assert.*;

public class AccessAccountsTest {
    private AccessAccounts accessAccounts;
    private List<Account> testAccountList;
    private int numOfAccounts;
    private Account templateAccount;

    @Before
    public void setup()
    {
        accessAccounts = new AccessAccounts();
    }

    @After
    public void tearDown()
    {
        accessAccounts = null;
    }

    @Test
    public void testNullAccountAccess()
    {
        System.out.println("\nStarting test null account access.\n");
        assertNotNull(accessAccounts);
        System.out.println("\nFinished test null account access.\n");
    }

    @Test
    public void testGetAccounts()
    {
        System.out.println("\nStart testing get account.\n");
        testAccountList = accessAccounts.getAccounts();
        numOfAccounts = testAccountList.size();
        assertTrue(numOfAccounts == 6);
        System.out.println("\nfinished testing get account.\n");
    }

    @Test
    public void testInsertAccount()
    {
        System.out.println("\nStart testing testInsertAccount.\n");
        templateAccount = new Account(7, "huahua");//insert a new account
        //before inserting, the account list should exist 6 accounts
        assertTrue(accessAccounts.getAccounts().size() == 6);
        accessAccounts.insertAccount(templateAccount);
        //after inserting, the account list should exist 7 accounts
        assertTrue(accessAccounts.getAccounts().size() == 7);
        //delete the added account
        accessAccounts.deleteAccount(templateAccount);
        System.out.println("\nfinished testing testInsertAccount.\n");
    }

    @Test
    public void testUpdateAccount()
    {
        System.out.println("\nStart testing testUpdateAccount.\n");
        templateAccount = new Account(7, "huahua");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        templateAccount.setUserName("newName");
        assertTrue(accessAccounts.updateAccount(templateAccount).getUserID() == 7 &&
                accessAccounts.updateAccount(templateAccount).getUserName().equals("newName"));
        accessAccounts.deleteAccount(templateAccount);
        System.out.println("\nfinished testing testUpdateAccount.\n");
    }

    @Test
    public void testDeleteAccount()
    {
        System.out.println("\nStart testing testDeleteAccount.\n");
        templateAccount = new Account(7, "huahua");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 7);
        accessAccounts.deleteAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 6);
        System.out.println("\nfinished testing testDeleteAccount.\n");
    }
}
