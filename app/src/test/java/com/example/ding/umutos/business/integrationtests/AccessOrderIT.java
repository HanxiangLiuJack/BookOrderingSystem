package com.example.ding.umutos.business.integrationtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.objects.OrderInfo;
import com.example.ding.umutos.utils.TestUtils;
import com.example.ding.umutos.business.AccessOrders;
import static junit.framework.Assert.assertNotNull;

import static junit.framework.Assert.assertTrue;

public class AccessOrderIT {

    private AccessOrders accessOrders;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.accessOrders = new AccessOrders();
    }

    @After
    public void tearDown() {
        TestUtils.delete();
        accessOrders = null;
    }

    @Test
    public void testNullOwnerAccess() {
        System.out.println("\nStarting test null testNullOwnerAccess.\n");
        assertNotNull(accessOrders);
        System.out.println("\nFinished test null testNullOwnerAccess.\n");
    }

    @Test
    public void testOrderHistory() {
        System.out.println("\nStarting test testOrderHistory\n");
        List<Order> orders = accessOrders.sellerOrderHistory("Tianhua Xu");
        assertTrue(orders.size() == 1);
        orders = accessOrders.buyerOrderHistory("Hanxiang Liu");
        assertTrue(orders.size() == 1);
        System.out.println("\nStarting test testInsertOrder\n");

    }

    @Test
    public void testInsertOrder()
    {
        System.out.println("\nStarting test testInsertOrder\n");
        OrderInfo orderInfo = new OrderInfo("a","b","c","d","e");
        Order newOrder = new Order("newBook", "Hanxiang Liu", "Tianhua Xu", 100, orderInfo);

        assertTrue(accessOrders.insertOrder(newOrder));
        assertTrue(accessOrders.buyerOrderHistory("Hanxiang Liu").size() == 2);
        System.out.println("\nEnd test testInsertOrder\n");
    }

}
