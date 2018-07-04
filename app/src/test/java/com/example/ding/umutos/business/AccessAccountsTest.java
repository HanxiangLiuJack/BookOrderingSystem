package com.example.ding.umutos.business;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.persistence.AccountPersistence;

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

//        final List <Account> accounts = new ArrayList<>();
//        accounts.add(new Account(1,"Yunlong Liu","1234"));
//        accounts.add(new Account(2,"Yu Gu","2234"));
//        accounts.add(new Account(3,"Hanxiang Liu","3234"));
//        accounts.add(new Account(4,"Tianhua Xu","4234"));
//        accounts.add(new Account(5,"Zitao Zheng","5678"));
//        accounts.add(new Account(6,"Xiao Peng","2234"));
//        when(accountPersistence.getAccountSequential()).thenReturn(accounts);

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
    {   /*
        System.out.println("\nStart testing get account.\n");
        testAccountList = accessAccounts.getAccounts();
        numOfAccounts = testAccountList.size();
        assertTrue(numOfAccounts == 6);
        System.out.println("\nfinished testing get account.\n");
        */
        System.out.println("\nStart testing get account.\n");
        final List <Account> accounts = new ArrayList<>();
        accounts.add(new Account(1,"Yunlong Liu","1234"));
        accounts.add(new Account(2,"Yu Gu","2234"));
        accounts.add(new Account(3,"Hanxiang Liu","3234"));
        accounts.add(new Account(4,"Tianhua Xu","4234"));
        accounts.add(new Account(5,"Zitao Zheng","5678"));
        accounts.add(new Account(6,"Xiao Peng","2234"));
        List<Account> temp;
        when(accountPersistence.getAccountSequential()).thenReturn(accounts);

        temp = accessAccounts.getAccounts();
        assertTrue(temp.equals(accounts));

        verify(accountPersistence).getAccountSequential();
        System.out.println("\nfinished testing get account.\n");
    }

    @Test
    public void testGetAccountByID()
    {

        /*
        System.out.println("\nStart testing GetAccountByID.\n");
        //test get a exist user
        assertTrue(accessAccounts.getAccountByID(1).getUserID() == 1);
        //test a user does not exist
        assertNull(accessAccounts.getAccountByID(100));
        System.out.println("\nEnd testing GetAccountByID.\n");
        */
        System.out.println("\nStart testing GetAccountByID.\n");

        final Account account =  new Account (3,"Hanxiang Liu","3234");
        Account result ;
        when(accountPersistence.getAccountByID(3)).thenReturn(account);

        result = accessAccounts.getAccountByID(3);

        assertTrue(result.equals(account));

        verify(accountPersistence).getAccountByID(3);

        System.out.println("\nEnd testing GetAccountByID.\n");

    }

    @Test
    public void testInsertAccount()
    {
        /*
        System.out.println("\nStart testing testInsertAccount.\n");
        templateAccount = new Account("huahua", "7797047");//insert a new account

        //before inserting, the account list should exist 6 accounts
        assertTrue(accessAccounts.getAccounts().size() == 6);

        boolean insertOrNot = accessAccounts.insertAccount(templateAccount);
        assertTrue(insertOrNot);

        //after inserting, the account list should exist 7 accounts
        assertTrue(accessAccounts.getAccounts().size() == 7);

        //delete the added account
        accessAccounts.deleteAccount(templateAccount);
        System.out.println("\nfinished testing testInsertAccount.\n");
        */

        System.out.println("\nStart testing testInsertAccount.\n");


        final Account account =  new Account (7,"Zapp","54213");
        Boolean result;
        Account temp;
        when(accountPersistence.insertAccount(account)).thenReturn(account);

        result = accessAccounts.insertAccount(account);
        assertTrue(result.equals(true));

        verify(accountPersistence).insertAccount(account);

        System.out.println("\nfinished testing testInsertAccount.\n");

    }

    @Test
    public void testUpdateAccount()
    {
        /*
        System.out.println("\nStart testing testUpdateAccount.\n");
        templateAccount = new Account("huahua", "7797047");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        templateAccount.setUserName("newName");
        assertTrue(accessAccounts.updateAccount(templateAccount));
        assertTrue(templateAccount.getUserID() == 7 && templateAccount.getUserName().equals("newName"));
        accessAccounts.deleteAccount(templateAccount);
        System.out.println("\nfinished testing testUpdateAccount.\n");
        */
        System.out.println("\nStart testing testUpdateAccount.\n");

        final List <Account> accounts = new ArrayList<>();

        final Account account = new Account (3,"Liu Hanxiang","6666");
        Boolean result ;
        Account temp ;

        when(accountPersistence.updateAccount(account)).thenReturn(account);

        result = accessAccounts.updateAccount(account);
        assertTrue(result.equals(true));

        verify(accountPersistence).updateAccount(account);


        System.out.println("\nfinished testing testUpdateAccount.\n");
    }



    @Test
    public void testDeleteAccount()
    {
        /*
        System.out.println("\nStart testing testDeleteAccount.\n");
        templateAccount = new Account("huahua", "7797047");//insert a new account
        accessAccounts.insertAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 7);
        accessAccounts.deleteAccount(templateAccount);
        assertTrue(accessAccounts.getAccounts().size() == 6);
        System.out.println("\nfinished testing testDeleteAccount.\n");
        */

        System.out.println("\nStart testing testDeleteAccount.\n");

        List<Account> temp = new ArrayList<>();
        temp.add(new Account(6,"Xiao Peng","2234"));
        when(accountPersistence.getAccountSequential()).thenReturn(temp);

        final Account account = new Account(6,"Xiao Peng","2234");

        doNothing().when(accountPersistence).deleteAccount(account);

        accessAccounts.deleteAccount(account);

        verify(accountPersistence).deleteAccount(account);

        System.out.println("\nfinished testing testDeleteAccount.\n");
    }
}
