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
        assertNotNull(accessAccounts);
    }

    @Test
    public void testGetAccounts()
    {
        testAccountList = accessAccounts.getAccounts();
        numOfAccounts = testAccountList.size();
        assertTrue(numOfAccounts == 6);
    }

    @Test
    public void testInsertAccount()
    {
        templateAccount = new Account(7, "huahua");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 7);
        accessAccounts.deleteAccount(templateAccount);
    }

    @Test
    public void testUpdateAccount()
    {
        templateAccount = new Account(7, "huahua");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        templateAccount.setUserName("newName");
        assertTrue(accessAccounts.updateAccount(templateAccount).getUserID() == 7 &&
                accessAccounts.updateAccount(templateAccount).getUserName().equals("newName"));
        accessAccounts.deleteAccount(templateAccount);
    }

    @Test
    public void testDeleteAccount()
    {
        templateAccount = new Account(7, "huahua");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 7);
        accessAccounts.deleteAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 6);
    }
}
