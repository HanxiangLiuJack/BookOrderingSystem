package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Order;

//Build pattern
public class OrderBuilder {

    private Order order;

    public OrderBuilder(Order o)
    {
        this.order = o;
    }

    public void setFirstName(final String fn)
    {
        order.setFirstName(fn);
    }

    public void setLastName(final String fn)
    {
        order.setLastName(fn);
    }

    public void setPostCode(final String fn)
    {
        order.setPostCode(fn);
    }

    public void setPhoneNumber(final String fn)
    {
        order.setPhoneNumber(fn);
    }

    public void setAddress(final String fn)
    {
        order.setAddress(fn);
    }

}
