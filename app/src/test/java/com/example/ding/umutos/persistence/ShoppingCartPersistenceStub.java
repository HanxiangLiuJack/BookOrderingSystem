package com.example.ding.umutos.persistence;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import com.example.ding.umutos.objects.Wish;

public class ShoppingCartPersistenceStub implements ShoppingCartPersistence {

    private List<Wish> shoppingCart;

    public ShoppingCartPersistenceStub() {
        this.shoppingCart = new ArrayList<>();
        shoppingCart.add(new Wish(1,11.22,"Tianhua Xu","Essential Scrum"));
        shoppingCart.add(new Wish(2,9.95,"Tianhua Xu","Economic and Social History of Medieval Europe"));
        shoppingCart.add(new Wish(3,11.66,"Tianhua Xu","An Economic and Social History of Later Medieval Europe");
        shoppingCart.add(new Wish(4,30.22,"Tianhua Xu","The Middle Ages"));
        shoppingCart.add(new Wish(5,22.45,"Tianhua Xu","The Guilty Wife"));
        shoppingCart.add(new Wish(6,33.22,"Tianhua Xu","One Way"));
        shoppingCart.add(new Wish(7,22.55,"Tianhua Xu","Sarah Dunn"));
    }


    //Print Book List
    @Override
    public List<Book> shoppingCartSequential() {
        return Collections.unmodifiableList(shoppingCart);
    }


    @Override
    public void insertShoppingCart(Book currentBook, String userName) {
        if(userName.equals("Tianhua Xu"))
            shoppingCart.add(currentBook);
    }


    @Override
    public Book searchShoppingCart(int id) {
        Book searchBook = null;

        for (int i = 0; i < shoppingCart.size()&&id>=0; i++) {
            if(shoppingCart.get(i).getBookID()==id)
                searchBook = shoppingCart.get(i);
        }
        return searchBook;
    }


    @Override
    public List<Book> getShoppingCartSequential(String userName) {
        List<Book> newList = new ArrayList<>();
        if(userName.equals("Tianhua Xu"))
            newList = shoppingCart;
        return newList;
    }


    @Override
    public void deleteBookfromShoppingCart(int id,String userName) {
        int index;
        if(userName.equals("Tianhua Xu")) {
            index = shoppingCart.indexOf(searchShoppingCart(id));
            if (index >= 0)
                shoppingCart.remove(index);
        }
    }


    @Override
    public void clearShoppingCart(String userName){
        if(userName.equals("Tianhua Xu")){
            shoppingCart.clear();
        }
    }


}
