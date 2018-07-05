package com.example.ding.umutos.business;


import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.persistence.OrderPersistence;

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
    public List<Order> orderHistory(int id, int number) {
        List<Order> history;

        if (number == 1) {
            history = orderPersistence.getBuyerOrders(id);
        } else {
            history = orderPersistence.getSellerOrders(id);
        }
        return history;
    }

    public List<Order> getOrder()
    {
        return orderPersistence.getOrders();
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





