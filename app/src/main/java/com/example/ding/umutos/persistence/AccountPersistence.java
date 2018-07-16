package com.example.ding.umutos.persistence;

import java.util.List;

import com.example.ding.umutos.objects.Account;

public interface AccountPersistence{
    List<Account> getAccountSequential();

    Account getAccountByUserName(String userName);

    Account insertAccount(Account currentAccount);

    Account updateAccount(Account currentAccount);

    void deleteAccount(Account currentAccount);

    void updateRating(String userName, double rate, int ratedPerson);
}