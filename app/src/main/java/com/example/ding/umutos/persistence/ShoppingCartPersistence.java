package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;

import java.util.List;

public interface ShoppingCartPersistence {

    void insertShoppingCart(Book currentBook, int userID);

    void deleteBookfromShoppingCart(int bookID);

    Book searchShoppingCart(int id);

    List<Book> getShoppingCartSequential(int userID);

    void clearShoppingCart(int userID);


}
