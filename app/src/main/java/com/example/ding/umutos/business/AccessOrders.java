package com.example.ding.umutos.business;


import android.util.Log;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.persistence.OrderPersistence;
import com.example.ding.umutos.objects.Account;

import java.util.List;

public class AccessOrders {


    private OrderPersistence orderPersistence;

    public AccessOrders() {
        orderPersistence = Service.getOrderPersistence();
    }


    public AccessOrders(final OrderPersistence orderPersistence) {
        this();
        this.orderPersistence = orderPersistence;
    }


    //need to test
    public List<Order> orderHistory(int userID, int userType) {
        List<Order> history;

        if (userType == 1) {
            history = orderPersistence.getBuyerOrders(userID);
        } else {
            history = orderPersistence.getSellerOrders(userID);
        }
        return history;
    }


    public boolean insertOrder(Order currentOrder){
        OrderValidator validator = new OrderValidator();
        if(currentOrder != null) {
            if(validator.validateOrder(currentOrder)) {
                orderPersistence.insertOrder(currentOrder);
                return true;
            }
        }
        return false;
    }

}





