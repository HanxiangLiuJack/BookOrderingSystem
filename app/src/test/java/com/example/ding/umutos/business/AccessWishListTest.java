package com.example.ding.umutos.business;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.ding.umutos.objects.Book;
import static junit.framework.Assert.*;

import com.example.ding.umutos.persistence.WishListPersistence;


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
    public void
}
