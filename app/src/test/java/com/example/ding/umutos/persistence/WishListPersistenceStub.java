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
        wishList.add(new Wish(1,"Tianhua Xu","a","Essential Scrum"));
        wishList.add(new Wish(2,"Tianhua Xu","b","Economic and Social History of Medieval Europe"));
        wishList.add(new Wish(3,"Tianhua Xu","c","An Economic and Social History of Later Medieval Europe"));
        wishList.add(new Wish(4,"Tianhua Xu","d","The Middle Ages"));
        wishList.add(new Wish(5,"Tianhua Xu","e","The Guilty Wife"));
        wishList.add(new Wish(6,"Tianhua Xu","f","One Way"));
        wishList.add(new Wish(7,"Tianhua Xu","g","Sarah Dunn"));  
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
    public void insertWishList(Wish wish){
        if(wish.getUserName().equals("Tianhua Xu"))
            wishList.add(wish);
    }

    @Override
    public List<Wish> getUserWishListSequential(String userName){
        List<Wish> newList = new ArrayList<>();
        if(userName.equals("Tianhua Xu"))
            newList = wishList;
        return newList;
    }

}
