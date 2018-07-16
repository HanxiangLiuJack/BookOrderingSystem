package com.example.ding.umutos.business.unittests;

import com.example.ding.umutos.business.AccessWishlists;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import com.example.ding.umutos.objects.Wish;
import static junit.framework.Assert.*;

import com.example.ding.umutos.persistence.WishListPersistence;
import com.example.ding.umutos.persistence.WishListPersistenceStub;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
public class AccessWishListTest {

    private WishListPersistence wishListPersistence;
    private AccessWishlists accessWishlists;

    @Before
    public void setup()
    {
        wishListPersistence = mock(WishListPersistence.class);
        accessWishlists = new AccessWishlists(wishListPersistence);
    }

    @After
    public void tearDown()
    {
        wishListPersistence = null;
        accessWishlists = null;
    }

    @Test
    public void testGetWishList()
    {
        System.out.println("\nStart testing testGetWishList.\n");
        List<Wish> wishList = new ArrayList<>();

        when(wishListPersistence.getWishListSequential()).thenReturn(wishList);

        assertTrue(accessWishlists.getWishList().equals(wishList));
        verify(wishListPersistence).getWishListSequential();

        wishListPersistence = new WishListPersistenceStub();
        accessWishlists = new AccessWishlists(wishListPersistence);

        assertTrue(accessWishlists.getWishList().size() == 7);
        System.out.println("\nEnd testing testGetWishList.\n");
    }

    @Test
    public void testGetUserWishList()
    {
        System.out.println("\nStart testing testGetUserWishList.\n");
        List<Wish> wishList = new ArrayList<>();

        when(wishListPersistence.getUserWishListSequential("Tianhua")).thenReturn(wishList);

        assertTrue(accessWishlists.getUserWishLists("Tianhua Xu").equals(wishList));
        verify(wishListPersistence).getUserWishListSequential("Tianhua Xu");

        wishListPersistence = new WishListPersistenceStub();
        accessWishlists = new AccessWishlists(wishListPersistence);

        assertTrue(accessWishlists.getUserWishLists("Tianhua Xu").size() == 7);
        System.out.println("\nEnd testing testGetUserWishList.\n");
    }

    @Test
    public void testDeleteWishList()
    {
        System.out.println("\nStart testing testDeleteWishList.\n");
        Wish desire = new Wish( "a", "b", "c");
        doNothing().when(wishListPersistence).deleteWishList(4, "Tianhua Xu");
        when(wishListPersistence.searchWishList(4)).thenReturn(desire);
        accessWishlists.deleteWishList(4, "Tianhua Xu");

        verify(wishListPersistence).deleteWishList(4, "Tianhua Xu");

        wishListPersistence = new WishListPersistenceStub();
        accessWishlists = new AccessWishlists(wishListPersistence);

        accessWishlists.deleteWishList(4, "Tianhua Xu");
        assertTrue(accessWishlists.getUserWishLists("Tianhua Xu").size() == 6);
        System.out.println("\nEnd testing testDeleteWishList.\n");
    }

    @Test
    public void testInsertWishList()
    {
        System.out.println("\nStart testing testInsertWishList.\n");
        Wish desire = new Wish("Tianhua Xu", "a", "b");

        when(wishListPersistence.insertWishList(desire)).thenReturn(desire);
        assertTrue(accessWishlists.insertWishList(desire));
        verify(wishListPersistence).insertWishList(desire);

        wishListPersistence = new WishListPersistenceStub();
        accessWishlists = new AccessWishlists(wishListPersistence);

        accessWishlists.insertWishList(desire);
        assertTrue(accessWishlists.getUserWishLists("Tianhua Xu").size() == 8);
        System.out.println("\nEnd testing testInsertWishList.\n");
    }
}
