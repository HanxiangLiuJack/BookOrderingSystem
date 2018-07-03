package com.example.ding.umutos.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.ding.umutos.persistence.AccountPersistence;
import com.example.ding.umutos.objects.Account;

public class AccountPersistenceStub implements AccountPersistence{

    private List<Account> Accounts;

    public AccountPersistenceStub()
    {
        this.Accounts = new ArrayList<>();
        Accounts.add(new Account(1,"Yunlong Liu"));
        Accounts.add(new Account(2,"Yu Gu"));
        Accounts.add(new Account(3,"Hanxiang Liu"));
        Accounts.add(new Account(4,"Tianhua Xu"));
        Accounts.add(new Account(5,"Zitao Zheng"));
        Accounts.add(new Account(6,"Xiao Peng"));
    }

    @Override
    public List<Account> getAccountSequential()
    {
        return Collections.unmodifiableList(Accounts);
    }

    @Override
    public Account getAccountByID(int userID)
    {
        for(int i = 0; i < Accounts.size(); i++)
        {
            if(Accounts.get(i).getUserID() == userID)
                return Accounts.get(i);
        }
        return null;
    }

    @Override
    public Account insertAccount(Account currentAccount)
    {
        Accounts.add(currentAccount);
        return currentAccount;
    }

    @Override
    public Account updateAccount(Account currentAccount)
    {
        int index;

        index = Accounts.indexOf(currentAccount);
        if(index >= 0)
            Accounts.set(index, currentAccount);
        return currentAccount;
    }

    @Override
    public void deleteAccount(Account currentAccount)
    {
        int index;

        index = Accounts.indexOf(currentAccount);
        if(index >= 0)
            Accounts.remove(index);
    }


}
