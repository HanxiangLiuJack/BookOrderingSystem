package com.example.ding.umutos.business;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.ding.umutos.objects.Book;

import static junit.framework.Assert.*;

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
        List<Book> shoppingCart = new ArrayList<>();
        shoppingCart.add(new Book("a","b",1,"c","d",20,"huahua"));
        when(shoppingCartPersistence.getShoppingCartSequential("huahua")).thenReturn(shoppingCart);
        List<Book> result = accessShoppingCart.getUserShoppingCart("huahua");

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
        Wish desire = new Wish(1, 2.2, "a", "b");
        doNothing().when(shoppingCartPersistence).insertShoppingCart(desire,"Tianhua Xu");
        accessShoppingCart.insertShoppingCart(desire,"Tianhua Xu");
        verify(shoppingCartPersistence).insertShoppingCart(desire,"Tianhua Xu");

        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);

        assertTrue(accessShoppingCart.insertShoppingCart(desire,"Tianhua Xu"));

        assertTrue(accessShoppingCart.getUserShoppingCart("Tianhua Xu").size() == 8);
        System.out.println("\nEnd testing testInsertShoppingCart.\n");
    }

    @Test
    public void testDeleteBookFromShoppingCart()
    {
        System.out.println("\nStart testing testDeleteBookfromShoppingCart.\n");
        Wish desire = new Wish(1, 2.2, "a", "b");
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
        assertTrue(accessShoppingCart.getTotalPrice("Tianhua Xu") == 141.17000000000002);
        System.out.println("\nStart testing testGetTotalPrice.\n");
    }

    @Test
    public void testClearShoppingCart()
    {
        System.out.println("\nStart testing testClearShoppingCart.\n");
        doNothing().when(shoppingCartPersistence).clearShoppingCart("Tianhua Xu");
        accessShoppingCart.clearShoppingCart("Tianhua Xu");
        verify(shoppingCartPersistence).clearShoppingCart("Tianhua Xu");

        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);

        accessShoppingCart.clearShoppingCart("Tianhua Xu");
        assertTrue(accessShoppingCart.getUserShoppingCart("Tianhua Xu").size() == 0);
        System.out.println("\nStart testing testClearShoppingCart.\n");

    }
}
