package com.example.ding.umutos.business;

import java.util.List;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.persistence.AccountPersistence;

public class AccessAccounts {

    private AccountPersistence accountPersistence;
    private List<Account> accounts;

    public AccessAccounts() {
        accountPersistence = Service.getAccountPersistence();
        accounts = null;
    }

    public AccessAccounts(final AccountPersistence accountPersistence) {
        this();
        this.accountPersistence = accountPersistence;
    }

    public List<Account> getAccounts() {
        accounts = accountPersistence.getAccountSequential();
        return accounts;
    }

    public Account getAccountByUserName(String userName) {
        return accountPersistence.getAccountByUserName(userName);
    }

    public boolean insertAccount(Account currentAccount) {
        if(currentAccount != null) {
            accountPersistence.insertAccount(currentAccount);
            return true;
        }
        return false;
    }

    public boolean updateAccount(Account currentAccount) {
        return accountPersistence.updateAccount(currentAccount) != null;
    }

    public void deleteAccount(Account currentAccount) {
        accountPersistence.deleteAccount(currentAccount);
    }

    public Account Login(String userName, String password) {
        Account targetAccount = null;
        getAccounts();
        for(int i = 0; i < accounts.size(); i++)
        {
            boolean sameUserName = accounts.get(i).getUserName().equals(userName);
            boolean samePassword = Integer.parseInt(accounts.get(i).getPassword()) == password.hashCode();
            if(samePassword && sameUserName)
                targetAccount = accounts.get(i);
        }
        return targetAccount;
    }

    public boolean userByUsername(String userName, List<Account> db) {
        for(int i = 0; i < db.size(); i++) {
            if(db.get(i).getUserName().equals(userName))
                return false;
        }
        return true;
    }
    
    public Account register(String userName, String passWord) {
        Account targetAccount = null;
        AccountValidator validator = new AccountValidator();
        getAccounts();
        if(this.userByUsername(userName, accounts) && validator.validatePassword(passWord))
        {
            targetAccount = new Account(userName, Integer.toString(passWord.hashCode()));
            this.insertAccount(targetAccount);
        }
        return targetAccount;
    }

    public double getAccountRate(String userName,List<Account> accounts)
    {
        double rate = 5;
        for(int j = 0; j < accounts.size(); j++)
        {
            if(accounts.get(j).getUserName().equals(userName))
                rate = accounts.get(j).getRate();
        }
        return rate;
    }

    public void RateUser(String sellerName, double rate)
    {
        Account seller = getAccountByUserName(sellerName);
        RateCalculator newCalculator = new RateCalculator();
        seller.setRate(newCalculator.calculateRate(seller,rate));
        accountPersistence.updateRating(sellerName, seller.getRate(), seller.getRatedPerson());
    }
}
