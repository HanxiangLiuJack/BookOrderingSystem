package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;

import java.util.List;

public interface WishListPersistence {

    List<Book> getWishListSequential();

    void deleteWishList(int id);

    Book insertWishList(Book currentBook);

    Book searchWishList(int id);

    List<Book> getUserWishListSequential(int userID);


}
