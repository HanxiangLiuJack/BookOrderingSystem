package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Wish;
import java.util.List;

public interface WishListPersistence {

    List<Wish> getWishListSequential();

    void deleteWishList(int id,String userName);

    Wish insertWishList(Wish wish);

    Wish searchWishList(int id);

    List<Wish> getUserWishListSequential(String userName);

}