package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Wish;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;

import java.util.List;

public class AccessShoppingCart {

    private ShoppingCartPersistence shoppingCartPersistence;
    private List<Wish> list;
    private List<Wish> userShoppingCart;
    private List<Wish> priceList;


    public AccessShoppingCart() {
        shoppingCartPersistence = Service.getShoppingCartPersistence();
        list = null;
    }

    public AccessShoppingCart(final ShoppingCartPersistence shoppingCartPersistence) {
        this();
        this.shoppingCartPersistence = shoppingCartPersistence;

    }


    public boolean insertShoppingCart(Wish wish,String userName) {
        if(wish!=null){
            shoppingCartPersistence.insertShoppingCart(wish,userName);
            return true;
        }
        return false;
    }

    public void deleteBookfromShoppingCart(int bookID, String userName) {
        if(searchShoppingCart(bookID) != null)
            shoppingCartPersistence.deleteBookfromShoppingCart(bookID, userName);
    }

    public Wish searchShoppingCart(int id) {
        return shoppingCartPersistence.searchShoppingCart(id);
    }


    public List<Wish> getUserShoppingCart(String userName) {
        userShoppingCart = shoppingCartPersistence.getShoppingCartSequential(userName);
        return userShoppingCart;
    }


    public double getTotalPrice(String userName){
        double totalPrice=0;
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
