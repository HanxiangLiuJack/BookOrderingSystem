package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;

import java.util.List;

public interface WishListPersistence {

    List<Book> getWishListSequential();

    void deleteWishList(int id);

    void insertWishList(Book currentBook,int userID);

    Book searchWishList(int id);

    List<Book> getUserWishListSequential(int useID);


}