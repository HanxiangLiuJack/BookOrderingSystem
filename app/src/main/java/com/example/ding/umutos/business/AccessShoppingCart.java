package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;

import java.util.List;

public class AccessShoppingCart {

    private ShoppingCartPersistence shoppingCartPersistence;
    private List<Book> list;
    private List<Book> userShoppingCart;
    private List<Book> priceList;


    public AccessShoppingCart() {
        shoppingCartPersistence = Service.getShoppingCartPersistence();
        list = null;
    }

    public AccessShoppingCart(final ShoppingCartPersistence shoppingCartPersistence) {
        this();
        this.shoppingCartPersistence = shoppingCartPersistence;

    }


    public boolean insertShoppingCart(Book currentBook,int userID) {
        if(currentBook!=null){
            shoppingCartPersistence.insertShoppingCart(currentBook,userID);
            return true;
        }
        return false;
    }

    public void deleteBookfromShoppingCart(int bookID) {
        if(searchShoppingCart(bookID) != null)
            shoppingCartPersistence.deleteBookfromShoppingCart(bookID);
    }

    public Book searchShoppingCart(int id) {
        return shoppingCartPersistence.searchShoppingCart(id);
    }


    public List<Book> getUserShoppingCart(int userID) {
        userShoppingCart = shoppingCartPersistence.getShoppingCartSequential(userID);
        return userShoppingCart;
    }


    public int getTotalPrice(int userID){
        int totalPrice=0;
        priceList =shoppingCartPersistence.getShoppingCartSequential(userID);
        for(int i=0;i<priceList.size();i++){
            totalPrice+=priceList.get(i).getPrice();

        }
        return totalPrice;
    }


    public void clearShoppingCart(int userID){
        shoppingCartPersistence.clearShoppingCart(userID);
    }



}
