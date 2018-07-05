package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderPersistenceStub implements OrderPersistence {

    private List<Order> orders;

    public OrderPersistenceStub()
    {
        this.orders = new ArrayList<>();
        String[] orderInfo1 = {"firstName1", "lastName1", "r3y0b6", "2046666666", "Mars"};
        String[] orderInfo2 = {"firstName2", "lastName2", "r3y0b7", "2046666667", "Heaven"};
        orders.add(new Order("book1", 1, 2, 1, orderInfo1));
        orders.add(new Order("book2", 3, 4, 3, orderInfo2));
    }

    @Override
    public Order insertOrder(Order currentOrder) {
        orders.add(currentOrder);
        return currentOrder;
    }


    public List<Order> getOrders()
    {
        return orders;
    }

    @Override
    public List<Order> getBuyerOrders(int userID) {
        return null;
    }

    @Override
    public List<Order> getSellerOrders(int userID) {
        return null;
    }
}
