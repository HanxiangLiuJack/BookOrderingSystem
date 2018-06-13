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

    public Account getAccountByID(int userID)
    {
        return accountPersistence.getAccountByID(userID);
    }

    public boolean insertAccount(Account currentAccount)
    {
        if(currentAccount != null) {
            accountPersistence.insertAccount(currentAccount);
            return true;
        }
        return false;
    }

    public boolean updateAccount(Account currentAccount)
    {
        return accountPersistence.updateAccount(currentAccount) != null;
    }

    public void deleteAccount(Account currentAccount)
    {
        accountPersistence.deleteAccount(currentAccount);
    }

}
