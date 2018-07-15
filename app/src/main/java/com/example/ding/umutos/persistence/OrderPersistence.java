package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Order;
import java.util.List;

public interface OrderPersistence {

    Order insertOrder(Order currentOrder);

    List<Order> getBuyerOrders(String userName);

    List<Order> getSellerOrders(String userName);

    List<Order> getOrders();
}
