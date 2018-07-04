package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.business.OrderValidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;


public class OrderValidatorTest {

    private Order validOrder;
    private Order invalidOrder;
    private OrderValidator orderValidator;

    @Before
    public void setup() {
        String [] validInfo = {"Liu","Hanxiang ","R3T","123456","abdRoad"};
       validOrder = new Order("abc",1,2,3.99,validInfo);
       String [] invalidInfo = {"","","","",""};
       invalidOrder = new Order("cde",1,2,19.99, invalidInfo);
       orderValidator = new OrderValidator();
    }

    @After
    public void tearDown()
    {
        validOrder = null; invalidOrder = null; orderValidator = null;
    }


    @Test
    public void testFirstName()
    {
        System.out.println("Test OrderInfo FirstName :");
        assertTrue(orderValidator.validateFirstName(validOrder.getBuyerFirstName()));
        assertTrue(!orderValidator.validateFirstName(invalidOrder.getBuyerFirstName()));
        System.out.println("Finish OrderInfo FirstName.");
    }


    @Test
    public void testLastName()
    {
        System.out.println("Test OrderInfo LastName :");
        assertTrue(orderValidator.validateLastName(validOrder.getBuyerLastName()));
        assertTrue(!orderValidator.validateLastName(invalidOrder.getBuyerLastName()));
        System.out.println("Finish OrderInfo LastName");
    }

    @Test
    public void testPostalCose(){
        System.out.println("Test PostalCode");
        assertTrue(orderValidator.validatePostCode(validOrder.getPostCode()));
        assertTrue(!orderValidator.validatePostCode(invalidOrder.getPostCode()));
        System.out.println("Finish PostalCode");
    }

    @Test
    public void testPhoneNum()
    {
        System.out.println("Test PhoneNum");
        assertTrue(orderValidator.validatePhoneNumber(validOrder.getPhoneNumber()));
        assertTrue(!orderValidator.validatePhoneNumber(invalidOrder.getPhoneNumber()));
        System.out.println("Finish PhoneNum");
    }

    @Test
    public void testAddress()
    {
        System.out.println("Test Address");
        assertTrue(orderValidator.validateAddress(validOrder.getAddress()));
        assertTrue(!orderValidator.validateAddress(invalidOrder.getAddress()));
        System.out.println("Finish Address");

    }


}
