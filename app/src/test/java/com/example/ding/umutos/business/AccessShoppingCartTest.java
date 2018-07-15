package com.example.ding.umutos.business;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.ding.umutos.objects.Book;
import static junit.framework.Assert.*;

import com.example.ding.umutos.persistence.ShoppingCartPersistence;


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
        Book desiredBook = new Book("a","b",1,"c","d",20,"huahua");
        doNothing().when(shoppingCartPersistence).insertShoppingCart(desiredBook,"Tianhua Xu");
        accessShoppingCart.insertShoppingCart(desiredBook,"Tianhua Xu");
        verify(shoppingCartPersistence).insertShoppingCart(desiredBook,"Tianhua Xu");

        shoppingCartPersistence = new ShoppingCartPersistenceStub();
        accessShoppingCart = new AccessShoppingCart(shoppingCartPersistence);

        assertTrue(accessShoppingCart.insertShoppingCart(desiredBook,"Tianhua Xu"));
        assertFalse(accessShoppingCart.insertShoppingCart(desiredBook,"abc"));

        assertTrue(accessShoppingCart.getUserShoppingCart("Tianhua Xu").size() == 8);
        System.out.println("\nEnd testing testInsertShoppingCart.\n");
    }

    @Test
    public void testDeleteBookFromShoppingCart()
    {
        System.out.println("\nStart testing testDeleteBookfromShoppingCart.\n");
        Book desiredBook = new Book("a","b",1,"c","d",20,"huahua");
        desiredBook.setBookID(1);
        doNothing().when(shoppingCartPersistence).deleteBookfromShoppingCart(desiredBook.getBookID(), "Tianhua Xu");

        accessShoppingCart.deleteBookfromShoppingCart(desiredBook.getBookID(), "Tianhua Xu");
        verify(shoppingCartPersistence).deleteBookfromShoppingCart(desiredBook.getBookID(), "Tianhua Xu");

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
        assertTrue(accessShoppingCart.getTotalPrice("Tianhua Xu") = );
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
