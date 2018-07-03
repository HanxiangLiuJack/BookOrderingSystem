package com.example.ding.umutos.business;


import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.persistence.OrderPersistence;
import com.example.ding.umutos.objects.Account;


import java.util.Collections;
import java.util.List;

public class AccessOrders {


    private OrderPersistence orderPersistence;
    private List<Order> order;


    public AccessOrders() {
        orderPersistence = Service.getOrderPersistence();
        history = null;
    }


    public AccessOrders(final OrderPersistence orderPersistence) {
        this();
        this.orderPersistence = orderPersistence;
    }


    //need to test
    public List<Order> orderHistory(Account user, int number) {
        List<Order> history;

        if (number == 0) {
            history = orderPersistence.getBuyerOrders(user.getUserID());
        } else {
            history = orderPersistence.getSellerOrders(user.getUserID());
        }
        return history;
    }


}





