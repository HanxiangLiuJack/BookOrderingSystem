package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;

import java.util.List;

public interface WishListPersistence {

    List<Wish> getWishListSequential();

    void deleteWishList(int id,String userName);

    void insertWishList(Wish wish,String userName);

    Wish searchWishList(int id);

    List<Wish> getUserWishListSequential(String userName);

}