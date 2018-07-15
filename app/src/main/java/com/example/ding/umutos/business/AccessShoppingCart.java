package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Item;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;

import java.util.List;

public class AccessShoppingCart {

    private ShoppingCartPersistence shoppingCartPersistence;
    private List<Item> list;
    private List<Item> userShoppingCart;
    private List<Item> priceList;


    public AccessShoppingCart() {
        shoppingCartPersistence = Service.getShoppingCartPersistence();
        list = null;
    }

    public AccessShoppingCart(final ShoppingCartPersistence shoppingCartPersistence) {
        this();
        this.shoppingCartPersistence = shoppingCartPersistence;

    }


    public boolean insertShoppingCart(Item item,String userName) {
        if(item!=null){
            shoppingCartPersistence.insertShoppingCart(item,userName);
            return true;
        }
        return false;
    }

    public void deleteBookfromShoppingCart(int bookID, String userName) {
        if(searchShoppingCart(bookID) != null)
            shoppingCartPersistence.deleteBookfromShoppingCart(bookID, userName);
    }

    public Item searchShoppingCart(int id) {
        return shoppingCartPersistence.searchShoppingCart(id);
    }


    public List<Item> getUserShoppingCart(String userName) {
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
