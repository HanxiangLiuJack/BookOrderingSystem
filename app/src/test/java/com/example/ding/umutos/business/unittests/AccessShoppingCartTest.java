package com.example.ding.umutos.business.unittests;

import com.example.ding.umutos.business.AccessShoppingCart;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.ding.umutos.objects.Item;
import static junit.framework.Assert.*;

import com.example.ding.umutos.objects.OrderInfo;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;
import com.example.ding.umutos.persistence.ShoppingCartPersistenceStub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
public class AccessShoppingCartTest {

    private AccessShoppingCart accessShoppingCart;
    private ShoppingCartPersistence shoppingCartPersistence;

    @Before
    public void setup()
    {
        shoppingCartPersistence = mock(ShoppingCartPersistence.class);
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);
    }

    @After
    public void tearDown()
    {
        shoppingCartPersistence = null;
        accessShoppingCart = null;
    }

    @Test
    public void testGetUserShoppingCart()
    {
        System.out.println("\nStart testing testGetUserShoppingCart.\n");
        List<Item> shoppingCart = new ArrayList<>();
        //shoppingCart.add(new Item("a",20,"c",99));
        when(shoppingCartPersistence.getShoppingCartSequential("huahua")).thenReturn(shoppingCart);
        List<Item> result = accessShoppingCart.getUserShoppingCart("huahua");

        assertTrue(result.equals(shoppingCart));

        verify(shoppingCartPersistence).getShoppingCartSequential("huahua");

        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);

        shoppingCart = accessShoppingCart.getUserShoppingCart("Tianhua Xu");
        assertTrue(shoppingCart.size() == 7);
        System.out.println("\nEnd testing testGetUserShoppingCart.\n");
    }

    @Test
    public void testInsertShoppingCart()
    {
        System.out.println("\nStart testing testInsertShoppingCart.\n");
        Item desire = new Item("Tianhua Xu", 11, "a", 19);
        doNothing().when(shoppingCartPersistence).insertShoppingCart(desire);
        accessShoppingCart.insertShoppingCart(desire);
        verify(shoppingCartPersistence).insertShoppingCart(desire);

        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);

        assertTrue(accessShoppingCart.insertShoppingCart(desire));

        assertTrue(accessShoppingCart.getUserShoppingCart("Tianhua Xu").size() == 8);
        System.out.println("\nEnd testing testInsertShoppingCart.\n");
    }

    @Test
    public void testDeleteBookFromShoppingCart()
    {
        System.out.println("\nStart testing testDeleteBookfromShoppingCart.\n");
        Item desire = new Item("Tianhua Xu", 11, "a", 19);
        doNothing().when(shoppingCartPersistence).deleteBookfromShoppingCart(desire.getBookID(), "Tianhua Xu");
        when(shoppingCartPersistence.searchShoppingCart(desire.getBookID())).thenReturn(desire);
        accessShoppingCart.deleteBookfromShoppingCart(desire.getBookID(), "Tianhua Xu");
        assertTrue(accessShoppingCart.searchShoppingCart(desire.getBookID()).equals(desire));

        verify(shoppingCartPersistence).deleteBookfromShoppingCart(desire.getBookID(), "Tianhua Xu");

        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);

        accessShoppingCart.deleteBookfromShoppingCart(5, "Tianhua Xu");
        assertTrue(accessShoppingCart.getUserShoppingCart("Tianhua Xu").size() == 6);

        System.out.println("\nStart testing testDeleteBookfromShoppingCart.\n");
    }

    @Test
    public void testGetTotalPrice()
    {
        System.out.println("\nStart testing testGetTotalPrice.\n");
        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);
        assertTrue(accessShoppingCart.getTotalPrice("Tianhua Xu") == 247);
        System.out.println("\nStart testing testGetTotalPrice.\n");
    }

    @Test
    public void testClearShoppingCart()
    {
        System.out.println("\nStart testing testClearShoppingCart.\n");
        OrderInfo orderInfo = new OrderInfo("fn","ln","pc","pn","add");
        doNothing().when(shoppingCartPersistence).clearShoppingCart("Tianhua Xu");
        accessShoppingCart.clearShoppingCart("Tianhua Xu",orderInfo);
        verify(shoppingCartPersistence).clearShoppingCart("Tianhua Xu");
        System.out.println("\nStart testing testClearShoppingCart.\n");

    }
}
