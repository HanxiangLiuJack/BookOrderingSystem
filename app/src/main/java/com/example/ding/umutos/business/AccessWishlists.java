package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;

import com.example.ding.umutos.objects.Wish;
import com.example.ding.umutos.persistence.WishListPersistence;

import java.util.List;

public class AccessWishlists {
    private WishListPersistence wishListPersistence;
    private List<Wish> list;
    private List<Wish> userWishLists;

    public AccessWishlists() {
        wishListPersistence = Service.getWishListPersistence();
        list = null;
    }

    public AccessWishlists(final WishListPersistence wishListPersistence) {
        this();
        this.wishListPersistence = wishListPersistence;

    }


    public void deleteWishList(int bookID,String userName) {
        if(searchWishList(bookID) != null)
            wishListPersistence.deleteWishList(bookID, userName);
    }


    public Wish searchWishList(int id) {
        return wishListPersistence.searchWishList(id);
    }


    public boolean insertWishList(Wish wish) {
        if(wish!=null){
            wishListPersistence.insertWishList(wish);
            return true;
        }
        return false;
    }



    public List<Wish> getWishList() {
        list = wishListPersistence.getWishListSequential();
        return list;
    }


    public List<Wish> getUserWishLists(String userName) {
        userWishLists = wishListPersistence.getUserWishListSequential(userName);
        return userWishLists;
    }


}
