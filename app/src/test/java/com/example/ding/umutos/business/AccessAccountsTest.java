package com.example.ding.umutos.business;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.ding.umutos.objects.Account;

import com.example.ding.umutos.persistence.AccountPersistence;

import com.example.ding.umutos.persistence.AccountPersistenceStub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


import static junit.framework.Assert.*;

public class AccessAccountsTest {

    private AccessAccounts accessAccounts;
    private AccountPersistence accountPersistence;

    @Before
    public void setup()
    {
        accountPersistence = mock(AccountPersistence.class);
        accessAccounts = new AccessAccounts(accountPersistence);
    }

    @After
    public void tearDown()
    {
        accessAccounts = null;
        accountPersistence = null;
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
        final List <Account> accounts = new ArrayList<>();
        accounts.add(new Account("Yunlong Liu","1234"));

        when(accountPersistence.getAccountSequential()).thenReturn(accounts);

        List<Account> temp = accessAccounts.getAccounts();
        assertTrue(temp.equals(accounts));

        verify(accountPersistence).getAccountSequential();

        accountPersistence = new AccountPersistenceStub();
        accessAccounts = new AccessAccounts(accountPersistence);
        List<Account> testAccountList = accessAccounts.getAccounts();
        int numOfAccounts = testAccountList.size();
        assertTrue(numOfAccounts == 6);
        System.out.println("\nfinished testing get account.\n");
    }

    @Test
    public void testGetAccountByID()
    {
        System.out.println("\nStart testing GetAccountByID.\n");

        final Account account =  new Account ("Hanxiang Liu","3234");
        when(accountPersistence.getAccountByID(3)).thenReturn(account);
        Account result = accessAccounts.getAccountByID(3);
        assertTrue(result.equals(account));
        verify(accountPersistence).getAccountByID(3);

        accountPersistence = new AccountPersistenceStub();
        accessAccounts = new AccessAccounts(accountPersistence);

        assertTrue(accessAccounts.getAccountByID(1).getUserID() == 1);

        assertNull(accessAccounts.getAccountByID(100));
        System.out.println("\nEnd testing GetAccountByID.\n");

    }

    @Test
    public void testInsertAccount()
    {
        System.out.println("\nStart testing testInsertAccount.\n");

        final Account account =  new Account ("Zapp","54213");

        when(accountPersistence.insertAccount(account)).thenReturn(account);

        Boolean result = accessAccounts.insertAccount(account);
        assertTrue(result.equals(true));

        verify(accountPersistence).insertAccount(account);

        accountPersistence = new AccountPersistenceStub();
        accessAccounts = new AccessAccounts(accountPersistence);

        Account templateAccount = new Account( "huahua", "123");//insert a new account

        //before inserting, the account list should exist 6 accounts
        assertTrue(accessAccounts.getAccounts().size() == 6);

        boolean insertOrNot = accessAccounts.insertAccount(templateAccount);
        assertTrue(insertOrNot);

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

        final Account account = new Account ("Liu Hanxiang","6666");

        when(accountPersistence.updateAccount(account)).thenReturn(account);

        Boolean result = accessAccounts.updateAccount(account);
        assertTrue(result.equals(true));

        verify(accountPersistence).updateAccount(account);

        accountPersistence = new AccountPersistenceStub();
        accessAccounts = new AccessAccounts(accountPersistence);

        Account templateAccount = new Account("huahua", "11234");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        templateAccount.setUserName("newName");
        assertTrue(accessAccounts.updateAccount(templateAccount));
        assertTrue(templateAccount.getUserName().equals("newName"));
        accessAccounts.deleteAccount(templateAccount);
        System.out.println("\nfinished testing testUpdateAccount.\n");
    }



    @Test
    public void testDeleteAccount()
    {
        System.out.println("\nStart testing testDeleteAccount.\n");
        final Account account = new Account("Xiao Peng","2234");

        doNothing().when(accountPersistence).deleteAccount(account);

        accessAccounts.deleteAccount(account);

        verify(accountPersistence).deleteAccount(account);

        accountPersistence = new AccountPersistenceStub();
        accessAccounts = new AccessAccounts(accountPersistence);

        Account templateAccount = new Account("huahua", "12312");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 7);
        accessAccounts.deleteAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 6);
        System.out.println("\nfinished testing testDeleteAccount.\n");
    }
}
