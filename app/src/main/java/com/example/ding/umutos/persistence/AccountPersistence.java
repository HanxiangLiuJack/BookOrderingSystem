package com.example.ding.umutos.persistence;

import java.util.List;

import com.example.ding.umutos.objects.Account;

public interface AccountPersistence{
    List<Account> getAccountSequential();

    Account insertAccount(Account currentAccount);

    Account updateAccount(Account currentAccount);

    void deleteAccount(Account currentAccount);
}