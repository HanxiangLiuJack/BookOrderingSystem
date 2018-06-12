package com.example.ding.umutos.business;

import java.util.Collections;
import java.util.List;
import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.persistence.AccountPersistence;

public class AccessAccounts {

    private AccountPersistence accountPersistence;
    private List<Account> accounts;

    public AccessAccounts()
    {
        accountPersistence = Service.getAccountPersistence();
        accounts = null;
    }

    public List<Account> getAccounts()
    {
        accounts = accountPersistence.getAccountSequential();
        return Collections.unmodifiableList(accounts);
    }

    public Account insertAccount(Account currentAccount)
    {
        if(currentAccount != null)
            return accountPersistence.insertAccount(currentAccount);
        return null;
    }

    public Account updateAccount(Account currentAccount)
    {
        return accountPersistence.updateAccount(currentAccount);
    }

    public void deleteAccount(Account currentAccount)
    {
        accountPersistence.deleteAccount(currentAccount);
    }
}
