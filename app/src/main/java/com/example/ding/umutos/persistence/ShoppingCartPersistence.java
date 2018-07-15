package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Wish;

import java.util.List;

public interface ShoppingCartPersistence {

    void insertShoppingCart(Wish wish, String userName);

    void deleteBookfromShoppingCart(int bookID, String userName);

    Wish searchShoppingCart(int id);

    List<Wish> getShoppingCartSequential(String userName);

    void clearShoppingCart(String userName);

    List<Wish> shoppingCartSequential();


}
