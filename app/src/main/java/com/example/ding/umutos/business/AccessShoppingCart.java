package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Item;
import com.example.ding.umutos.objects.OrderInfo;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;
import com.example.ding.umutos.persistence.BookPersistence;
import com.example.ding.umutos.persistence.OrderPersistence;


import java.util.ArrayList;
import java.util.List;

public class AccessShoppingCart {

    private ShoppingCartPersistence shoppingCartPersistence;
    private List<Item> priceList;
    private BookPersistence bookPersistence;
    private OrderPersistence orderPersistence;

    public AccessShoppingCart() {
        shoppingCartPersistence = Service.getShoppingCartPersistence();
        bookPersistence = Service.getBookPersistence();
        orderPersistence = Service.getOrderPersistence();
    }

    public AccessShoppingCart(final ShoppingCartPersistence shoppingCartPersistence) {
        this();
        this.shoppingCartPersistence = shoppingCartPersistence;

    }

    public boolean checkShoppingCartItem(Item item)
    {
        Item newItem = searchShoppingCart(item.getBookID());
        return newItem == null ||
                (newItem != null && !newItem.getUserName().equals(item.getUserName()));
    }


    public boolean insertShoppingCart(Item item) {
        if(item!=null){
            if(checkShoppingCartItem(item)) {
                shoppingCartPersistence.insertShoppingCart(item);
                return true;
            }
        }
        return false;
    }

    public void deleteBookfromShoppingCart(int bookID, String userName) {
        if(searchShoppingCart(bookID) != null)
            shoppingCartPersistence.deleteBookfromShoppingCart(bookID, userName);
    }

    public Item searchShoppingCart(int id) {
        return shoppingCartPersistence.searchShoppingCart(id);
    }


    public List<Item> getUserShoppingCart(String userName) {
        List<Item> userShoppingCart = shoppingCartPersistence.getShoppingCartSequential(userName);
        return userShoppingCart;
    }


    public double getTotalPrice(String userName){
        double totalPrice=0;
        priceList =this.getUserShoppingCart(userName);
        for(int i=0;i<priceList.size();i++){
            totalPrice+=priceList.get(i).getPrice();

        }
        return (double)Math.round(totalPrice*100d)/100d;
    }


    public List<Item> clearShoppingCart(String userName, OrderInfo orderInfo){
        List<Item> item = this.getUserShoppingCart(userName);
        List<Item> booksNotFound = null;
        Order newOrder;
        bookPersistence = Service.getBookPersistence();
        for(int i = 0; i < item.size(); i++)
        {
            if(bookPersistence.searchBook(item.get(i).getBookID()) == null)
            {
                if(booksNotFound == null)
                    booksNotFound = new ArrayList<>();
                booksNotFound.add(item.get(i));
            }
        }
        if(booksNotFound == null){
            for(int i=0;i<item.size();i++){
                String ownerName = bookPersistence.searchBook(item.get(i).getBookID()).getOwner();
                bookPersistence.deleteBook(item.get(i).getBookID());
                newOrder = new Order(item.get(i).getName(),item.get(i).getUserName(),ownerName,item.get(i).getPrice(),orderInfo);
                orderPersistence.insertOrder(newOrder);
            }
            shoppingCartPersistence.clearShoppingCart(userName);
        }
        return booksNotFound;

    }

}
