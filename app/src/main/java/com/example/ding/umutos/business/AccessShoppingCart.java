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


    public boolean insertShoppingCart(Book currentBook,String userName) {
        if(currentBook!=null){
            shoppingCartPersistence.insertShoppingCart(currentBook,userName);
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


    public List<Book> getUserShoppingCart(String userName) {
        userShoppingCart = shoppingCartPersistence.getShoppingCartSequential(userName);
        return userShoppingCart;
    }


    public int getTotalPrice(String userName){
        int totalPrice=0;
        priceList =shoppingCartPersistence.getShoppingCartSequential(userName);
        for(int i=0;i<priceList.size();i++){
            totalPrice+=priceList.get(i).getPrice();

        }
        return totalPrice;
    }


    public void clearShoppingCart(String userName){
        shoppingCartPersistence.clearShoppingCart(userName);
    }



}
