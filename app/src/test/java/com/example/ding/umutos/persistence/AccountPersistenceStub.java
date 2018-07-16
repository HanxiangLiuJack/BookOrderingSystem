package com.example.ding.umutos.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.example.ding.umutos.objects.Account;

public class AccountPersistenceStub implements AccountPersistence{

    private List<Account> Accounts;

    public AccountPersistenceStub() {
        this.Accounts = new ArrayList<>();
        Accounts.add(new Account("Yunlong Liu", "123"));
        Accounts.add(new Account("Yu Gu", "234"));
        Accounts.add(new Account("Hanxiang Liu", "345"));
        Accounts.add(new Account("Tianhua Xu", "456"));
        Accounts.add(new Account("Zitao Zheng", "567"));
        Accounts.add(new Account("Xiao Peng", "678"));

    }

    @Override
    public List<Account> getAccountSequential() {
        return Collections.unmodifiableList(Accounts);
    }

    @Override
    public Account getAccountByUserName(String userName) {
        for(int i = 0; i < Accounts.size(); i++)
        {
            if(Accounts.get(i).getUserName().equals(userName))
                return Accounts.get(i);
        }
        return null;
    }

    @Override
    public Account insertAccount(Account currentAccount) {
        Accounts.add(currentAccount);
        return currentAccount;
    }

    @Override
    public Account updateAccount(Account currentAccount) {
        int index;

        index = Accounts.indexOf(currentAccount);
        if(index >= 0)
            Accounts.set(index, currentAccount);
        return currentAccount;
    }

    @Override
    public void deleteAccount(Account currentAccount) {
        int index;

        index = Accounts.indexOf(currentAccount);
        if(index >= 0)
            Accounts.remove(index);
    }

    @Override
    public void updateRating(String userName, double rate, int ratedPerson)
    {

    }

}
