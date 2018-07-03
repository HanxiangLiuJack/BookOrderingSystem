package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.*;
import java.util.List;

public class OrderPersistenceStub implements OrderPersistence{

    private List<Order> orders;

    public OrderPersistenceStub()
    {
        Book b = new Book("name","name1", 1, "info", "COMP", 12.00, 11);
        Account a1 = new Account(1, "tianhua");
        Account a2 = new Account(2, "guyu");
        String[] orderinfo = {"tianhua", "xu", "r3y0b6", "2046666666", "100 appletree cres"};
        orders.add(new Order(b, a1, a2, orderinfo));
    }
}
