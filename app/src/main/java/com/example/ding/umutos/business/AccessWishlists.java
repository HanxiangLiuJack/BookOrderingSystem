package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.WishListPersistence;

import java.util.List;

public class AccessWishlists {
    private WishListPersistence wishListPersistence;
    private List<Book> list;
    private List<Book> userWishLists;

    public AccessWishlists() {
        wishListPersistence = Service.getWishListPersistence();
        list = null;
    }

    public AccessWishlists(final WishListPersistence wishListPersistence) {
        this();
        this.wishListPersistence = wishListPersistence;
    }


    public void deleteWishList(int bookID) {
        if(searchWishList(bookID) != null)
            wishListPersistence.deleteWishList(bookID);
    }


    public Book searchWishList(int id) {
        return wishListPersistence.searchWishList(id);
    }


    public boolean insertWishList(Book currentBook) {
        /*WishListValidator validator = new WishListValidator();
        if(currentBook != null) {
            if(validator.validateWishList(currentBook)) {
                wishListPersistence.insertWishList(currentBook);
                return true;
            }
        }
        return false;*/

        if(currentBook !=null){
            wishListPersistence.insertWishList(currentBook);
            return true;
        }
        return false;
    }



    public List<Book> getWishList() {
        list = wishListPersistence.getWishListSequential();
        return list;
    }


    public List<Book> getUserWishLists(int userID) {
        userWishLists = wishListPersistence.getUserWishListSequential(userID);
        return userWishLists;
    }


}
