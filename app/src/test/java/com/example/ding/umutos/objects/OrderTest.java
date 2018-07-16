package com.example.ding.umutos.objects;

import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;

    @Before
    public void setup() {
        OrderInfo orderInfo = new OrderInfo("Hanxiang","Liu","R3T","12345678","101 abcRoad");
        order = new Order("book1", "Tianhua Xu", "Tianhua Xu2", 100,orderInfo);
    }
    @After
    public void tearDown(){
        order = null;
    }

    @Test
    public void testGetBuyer() {
        System.out.println("Test getBuyer() :");
        assertTrue(order.getBuyerName().equals("Tianhua Xu"));
        System.out.println("Finish getBuyer() :");
    }

    @Test
    public void testGetSeller() {
        System.out.println("Test getSeller() :");

        assertTrue(order.getSellerName().equals("Tianhua Xu2"));

        System.out.println("Finish getSeller() :");
    }

    @Test
    public void testGetBuyerFirstName() {
        System.out.println("Test getBuyerFirstName : ");

        assertTrue("Hanxiang".equals(order.getBuyerFirstName()));

        System.out.println("Finish getBuyerFirstName. ");
    }

    @Test
    public void testGetBuyerLastName() {
        System.out.println("Test getBuyerFirstName : ");

        assertTrue("Liu".equals(order.getBuyerLastName()));

        System.out.println("Finish getBuyerFirstName. ");
    }


    @Test
    public void testPostCode() {
        System.out.println("Test getPostCode : ");

        assertTrue("R3T".equals(order.getPostCode()));

        System.out.println("Finish getPostCode. ");
    }


    @Test
    public void testPhoneNum() {
        System.out.println("Test getPhoneNumber. ");

        assertTrue("12345678".equals(order.getPhoneNumber()));

        System.out.println("Finish getPhoneNumber. ");
    }

    @Test
    public void testGetAddress() {
        System.out.println("Test getAddress ");

        assertTrue("101 abcRoad".equals(order.getAddress())) ;

        System.out.println("Finish getAddress");
    }
}
