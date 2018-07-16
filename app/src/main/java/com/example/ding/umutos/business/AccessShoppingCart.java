package com.example.ding.umutos.business;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Item;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;
import com.example.ding.umutos.persistence.BookPersistence;
import com.example.ding.umutos.persistence.OrderPersistence;


import java.util.ArrayList;
import java.util.List;

public class AccessShoppingCart {

    private ShoppingCartPersistence shoppingCartPersistence;
    private List<Item> list;
    private List<Item> priceList;
    private BookPersistence bookPersistence;
    private OrderPersistence orderPersitence;

    public AccessShoppingCart() {
        shoppingCartPersistence = Service.getShoppingCartPersistence();
        bookPersistence = Service.getBookPersistence();
        orderPersitence = Service.getOrderPersistence();
        list = null;
    }

    public AccessShoppingCart(final ShoppingCartPersistence shoppingCartPersistence) {
        this();
        this.shoppingCartPersistence = shoppingCartPersistence;

    }


    public boolean insertShoppingCart(Item item) {
        if(item!=null){
            shoppingCartPersistence.insertShoppingCart(item);
            return true;
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
        return totalPrice;
    }


    public List<Item> clearShoppingCart(String userName, String[] addressInfo){
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
                newOrder = new Order(item.get(i).getName(),item.get(i).getUserName(),ownerName,item.get(i).getPrice());
                OrderBuilder orderBuilder=new OrderBuilder(newOrder);
                orderBuilder.setPhoneNumber(addressInfo[2]);
                orderBuilder.setAddress(addressInfo[4]);
                orderBuilder.setPostCode(addressInfo[3]);
                orderBuilder.setLastName(addressInfo[1]);
                orderBuilder.setFirstName(addressInfo[0]);
                orderPersitence.insertOrder(newOrder);

            }
            shoppingCartPersistence.clearShoppingCart(userName);
        }
        return booksNotFound;

    }


}
