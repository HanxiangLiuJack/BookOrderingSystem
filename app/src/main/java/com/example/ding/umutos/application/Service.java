package com.example.ding.umutos.application;

import com.example.ding.umutos.persistence.BookPersistence;
import com.example.ding.umutos.persistence.AccountPersistence;
import com.example.ding.umutos.persistence.stubs.BookPersistenceStub;
import com.example.ding.umutos.persistence.stubs.AccountPersistenceStub;

public class Service {

    private static BookPersistence bookPersistence;
    private static AccountPersistence accountPersistence;

    public static synchronized BookPersistence getBookPersistence()
    {
        if (bookPersistence == null)
        {
            bookPersistence = new BookPersistenceStub();
        }

        return bookPersistence;
    }

    public static synchronized AccountPersistence getAccountPersistence()
    {
        if (accountPersistence == null)
        {
            accountPersistence = new AccountPersistenceStub();
        }

        return accountPersistence;
    }
}
