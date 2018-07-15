package com.example.ding.umutos.persistence;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import com.example.ding.umutos.objects.Item;

public class ShoppingCartPersistenceStub implements ShoppingCartPersistence {

    private List<Item> shoppingCart;

    public ShoppingCartPersistenceStub() {
        this.shoppingCart = new ArrayList<>();
        shoppingCart.add(new Item("Tianhua Xu",1,"Essential Scrum", 10));
        shoppingCart.add(new Item("Tianhua Xu",2,"Economic and Social History of Medieval Europe",11));
        shoppingCart.add(new Item("Tianhua Xu",3,"An Economic and Social History of Later Medieval Europe",12));
        shoppingCart.add(new Item("Tianhua Xu",4,"The Middle Ages",123));
        shoppingCart.add(new Item("Tianhua Xu",5,"The Guilty Wife", 23));
        shoppingCart.add(new Item("Tianhua Xu",6,"One Way",13));
        shoppingCart.add(new Item("Tianhua Xu",7,"Sarah Dunn", 55));
    }


    //Print Book List
    @Override
    public List<Item> shoppingCartSequential() {
        return Collections.unmodifiableList(shoppingCart);
    }


    @Override
    public void insertShoppingCart(Item item) {
        if(item.getUserName().equals("Tianhua Xu"))
            shoppingCart.add(item);
    }


    @Override
    public Item searchShoppingCart(int id) {
        Item item = null;
        for (int i = 0; i < shoppingCart.size()&&id>=0; i++) {
            if(shoppingCart.get(i).getBookID()==id)
                item = shoppingCart.get(i);
        }
        return item;
    }


    @Override
    public List<Item> getShoppingCartSequential(String userName) {
        List<Item> newList = new ArrayList<>();
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
