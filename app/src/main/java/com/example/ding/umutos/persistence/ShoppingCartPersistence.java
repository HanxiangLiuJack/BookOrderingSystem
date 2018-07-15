package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;

import java.util.List;

public interface ShoppingCartPersistence {

    void insertShoppingCart(Book currentBook, String userName);

    void deleteBookfromShoppingCart(int bookID, String userName);

    Book searchShoppingCart(int id);

    List<Book> getShoppingCartSequential(String userName);

    boolean clearShoppingCart(String userName);

    List<Book> shoppingCartSequential();


}
