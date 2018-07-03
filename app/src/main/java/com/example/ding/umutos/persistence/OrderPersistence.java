package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Order;
import java.util.List;

public interface OrderPersistence {

    Order insertOrder(Order currentOrder);

    List<Order> getBuyerOrders(int userID);

    List<Order> getSellerOrders(int userID);
}
