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



    public List<Order> buyerOrderHistory(String userName){
        List<Order> history;
        history = orderPersistence.getBuyerOrders(userName);
        return history;
    }

    public List<Order> sellerOrderHistory(String userName){
        List<Order> history;
        history = orderPersistence.getSellerOrders(userName);
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





