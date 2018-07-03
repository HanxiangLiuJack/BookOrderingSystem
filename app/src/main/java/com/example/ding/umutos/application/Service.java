package com.example.ding.umutos.application;

import com.example.ding.umutos.persistence.BookPersistence;
import com.example.ding.umutos.persistence.AccountPersistence;
import com.example.ding.umutos.persistence.OrderPersistence;
import com.example.ding.umutos.persistence.hsqldb.AccountPersistenceHSQLDB;
import com.example.ding.umutos.persistence.hsqldb.BookPersistenceHSQLDB;
import com.example.ding.umutos.persistence.hsqldb.OrderPersistenceHSQLDB;

public class Service {

    private static BookPersistence bookPersistence;
    private static AccountPersistence accountPersistence;
    private static OrderPersistence orderPersistence;

    public static synchronized BookPersistence getBookPersistence()
    {
        if (bookPersistence == null)
        {
            bookPersistence = new BookPersistenceHSQLDB(Main.getDBPathName());
        }

        return bookPersistence;
    }

    public static synchronized AccountPersistence getAccountPersistence()
    {
        if (accountPersistence == null)
        {
            accountPersistence = new AccountPersistenceHSQLDB(Main.getDBPathName());
        }

        return accountPersistence;
    }

    public static synchronized  OrderPersistence getOrderPersistence()
    {
        if(orderPersistence == null)
        {
            orderPersistence = new OrderPersistenceHSQLDB(Main.getDBPathName());
        }

        return orderPersistence;
    }
}
