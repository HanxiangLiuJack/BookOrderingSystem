package com.example.ding.umutos.objects;

import com.example.ding.umutos.objects.Order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;
    private String [] orderInfo = {"Hanxiang","Liu","R3T","12345678","101 abcRoad"} ;
    @Before
    public void setup()
    {order = new Order(new Book("aaa","bbb",1,"ccc","ddd",9.99,1),
           new Account( 1,"Buyer","1234"),
            new Account (2,"Seller","5678"),
            orderInfo);
    }
    @After
    public void tearDown(){order = null ;}

    @Test
    public void testGetBuyer()
    {
        System.out.println("Test getBuyer() :");

        assertTrue(1==order.getBuyer().getUserID() );
        assertTrue("Buyer".equals(order.getBuyer().getUserName()));
        assertTrue("1234".equals(order.getBuyer().getPassWord()));

        System.out.println("Finish getBuyer() :");
    }

    @Test
    public void testGetSeller()
    {
        System.out.println("Test getSeller() :");

        assertTrue(2 == order.getSeller().getUserID());
        assertTrue("Seller".equals(order.getSeller().getUserName()));
        assertTrue("5678".equals(order.getSeller().getPassWord()));

        System.out.println("Finish getSeller() :");
    }

    @Test
    public void testGetBuyerFirstName()
    {
        System.out.println("Test getBuyerFirstName : ");

        assertTrue("Hanxiang".equals(order.getBuyerFirstName()));

        System.out.println("Finish getBuyerFirstName. ");
    }

    @Test
    public void testGetBuyerLastName()
    {
        System.out.println("Test getBuyerFirstName : ");
        assertTrue("Liu".equals(order.getBuyerLastName()));
        System.out.println("Finish getBuyerFirstName. ");
    }


    @Test
    public void testPostCode()
    {
        System.out.println("Test getPostCode : ");

        assertTrue("R3T".equals(order.getPostCode()));

        System.out.println("Finish getPostCode. ");
    }


    @Test
    public void testPhoneNum()
    {
        System.out.println("Test getPhoneNumber. ");
        assertTrue("12345678".equals(order.getPhoneNumber()));
        System.out.println("Finish getPhoneNumber. ");
    }

    @Test
    public void testGetAddress()
    {
        System.out.println("Test getAddress ");
        assertTrue("101 abcRoad".equals(order.getAddress())) ;
        System.out.println("Finish getAddress");
    }
}
