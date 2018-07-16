package com.example.ding.umutos.business.integrationtests;

import com.example.ding.umutos.objects.Item;
import com.example.ding.umutos.utils.TestUtils;
import com.example.ding.umutos.business.AccessShoppingCart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class AccessShoppingCartIT {

        private AccessShoppingCart accessShoppingCart;
        private File tempDB;

        @Before
        public void setUp() throws IOException {
            this.tempDB = TestUtils.copyDB();
            this.accessShoppingCart = new AccessShoppingCart();
        }

        @Test
        public void testInsertShoppingCart(){
            Item item=new Item("Xiao Peng",1,"b",1.5);
            assertTrue(accessShoppingCart.insertShoppingCart(item));
            assertTrue(4==accessShoppingCart.getUserShoppingCart("Xiao Peng").size());
            System.out.println("Finished test ShoppingCart");
        }

        @Test
        public void TestDeleteBookfromShoppingCart(){
            accessShoppingCart.deleteBookfromShoppingCart(18,"Xiao Peng");
            assertTrue(2==accessShoppingCart.getUserShoppingCart("Xiao Peng").size());
            System.out.println("Finished test ShoppingCart");
        }

        @Test
        public void TestGetUserShoppingCart(){
            assertNotNull("first sequential course should not be null", accessShoppingCart.getUserShoppingCart("Xiao Peng"));
            System.out.println("Finished test ShoppingCart");
        }

        @Test
        public void TestGetTotalPrice(){
            assertTrue(181.63==accessShoppingCart.getTotalPrice("Xiao Peng"));
            System.out.println("Finished test ShoppingCart");
        }
        @Test
        public void TestClearShoppingCart(){
            accessShoppingCart.clearShoppingCart("Peng Xiao");
            assertNull(accessShoppingCart.getUserShoppingCart("Xiao Peng"));
            System.out.println("Finished test ShoppingCart");
        }


        @After
        public void tearDown() {
            this.tempDB.delete();
            accessShoppingCart= null;
        }


}
