package com.example.ding.umutos;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.persistence.AccountPersistence;

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
}
