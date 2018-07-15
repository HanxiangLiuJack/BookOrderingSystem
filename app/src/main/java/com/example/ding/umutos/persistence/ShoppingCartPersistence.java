package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Item;

import java.util.List;

public interface ShoppingCartPersistence {

    void insertShoppingCart(Item item);

    void deleteBookfromShoppingCart(int bookID, String userName);

    Item searchShoppingCart(int id);

    List<Item> getShoppingCartSequential(String userName);

    void clearShoppingCart(String userName);

    List<Item> shoppingCartSequential();


}
