package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Wish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishListPersistenceStub implements WishListPersistence{

    private List<Wish> wishList;

    public WishListPersistenceStub() {
        this.wishList = new ArrayList<>();
        wishList.add(new Wish("Tianhua Xu","a","Essential Scrum"));
        wishList.add(new Wish("Tianhua Xu","b","Economic and Social History of Medieval Europe"));
        wishList.add(new Wish("Tianhua Xu","c","An Economic and Social History of Later Medieval Europe"));
        wishList.add(new Wish("Tianhua Xu","d","The Middle Ages"));
        wishList.add(new Wish("Tianhua Xu","e","The Guilty Wife"));
        wishList.add(new Wish("Tianhua Xu","f","One Way"));
        wishList.add(new Wish("Tianhua Xu","g","Sarah Dunn"));
        wishList.get(4).setBookID(4);
    }


    @Override
    public List<Wish> getWishListSequential(){
        return Collections.unmodifiableList(wishList);
    }


    @Override
    public void deleteWishList(int id ,String userName) {
        int index;
        if(userName.equals("Tianhua Xu")) {
            index = wishList.indexOf(searchWishList(id));
            if (index >= 0)
                wishList.remove(index);
        }
    }

    @Override
    public Wish searchWishList(int id) {
        Wish wish = null;

        for (int i = 0; i < wishList.size()&&id>=0; i++) {
            if(wishList.get(i).getBookID()==id)
                wish = wishList.get(i);
        }
        return wish;
    }

    @Override
    public Wish insertWishList(Wish wish){
        if(wish.getUserName().equals("Tianhua Xu")) {
            wishList.add(wish);
        }
        return wish;
    }

    @Override
    public List<Wish> getUserWishListSequential(String userName){
        List<Wish> newList = new ArrayList<>();
        if(userName.equals("Tianhua Xu"))
            newList = wishList;
        return newList;
    }

}
