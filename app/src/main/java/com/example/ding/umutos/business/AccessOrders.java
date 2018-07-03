package com.example.ding.umutos.business;


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

    public boolean insertOrder(Order currentOrder)
    {
//        if(currentOrder != null) {
//            if(validateOrder(currentOrder)) {
//                orderPersistence.insertOrder(currentOrder);
//                return true;
//            }
//        }

        return false;
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





