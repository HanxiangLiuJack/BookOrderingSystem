package com.example.ding.umutos.application;

import com.example.ding.umutos.persistence.BookPersistence;
import com.example.ding.umutos.persistence.AccountPersistence;
import com.example.ding.umutos.persistence.OrderPersistence;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;
import com.example.ding.umutos.persistence.WishListPersistence;
import com.example.ding.umutos.persistence.hsqldb.AccountPersistenceHSQLDB;
import com.example.ding.umutos.persistence.hsqldb.BookPersistenceHSQLDB;
import com.example.ding.umutos.persistence.hsqldb.OrderPersistenceHSQLDB;
import com.example.ding.umutos.persistence.hsqldb.WishListPersistenceHSQLDB;
import com.example.ding.umutos.persistence.hsqldb.ShoppingCartPersistenceHSQLDB;

public class Service {

    private static BookPersistence bookPersistence = null;
    private static AccountPersistence accountPersistence = null;
    private static OrderPersistence orderPersistence = null;
    private static WishListPersistence wishListPersistence = null;
    private static ShoppingCartPersistence shoppingCartPersistence = null;

    public static synchronized BookPersistence getBookPersistence() {
        if (bookPersistence == null) {
            bookPersistence = new BookPersistenceHSQLDB(Main.getDBPathName());
        }
        return bookPersistence;
    }

    public static synchronized AccountPersistence getAccountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new AccountPersistenceHSQLDB(Main.getDBPathName());
        }
        return accountPersistence;
    }

    public static synchronized  OrderPersistence getOrderPersistence() {
        if(orderPersistence == null) {
            orderPersistence = new OrderPersistenceHSQLDB(Main.getDBPathName());
        }
        return orderPersistence;
    }
    public static synchronized WishListPersistence getWishListPersistence() {
        if(wishListPersistence == null) {
            wishListPersistence = new WishListPersistenceHSQLDB(Main.getDBPathName());
        }
        return wishListPersistence;
    }

    public static synchronized ShoppingCartPersistence getShoppingCartPersistence() {
        if(shoppingCartPersistence == null) {
            shoppingCartPersistence = new ShoppingCartPersistenceHSQLDB(Main.getDBPathName());
        }
        return shoppingCartPersistence;
    }



}
