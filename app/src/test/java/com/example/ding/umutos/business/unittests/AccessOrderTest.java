package com.example.ding.umutos.business.unittests;

import com.example.ding.umutos.business.AccessOrders;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.objects.OrderInfo;
import com.example.ding.umutos.persistence.OrderPersistence;
import com.example.ding.umutos.persistence.OrderPersistenceStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessOrderTest {
  private AccessOrders accessOrders;
  private OrderPersistence orderPersistence;

  @Before
    public void setup() {
      orderPersistence = mock(OrderPersistence.class);
      accessOrders = new AccessOrders(orderPersistence);
  }


    @After
    public void tearDown(){
      accessOrders = null ; orderPersistence = null ;
  }

    @Test
    public void testOrderHistory() {
      System.out.println("\nStarting test testOrderHistory\n");
      OrderInfo orderInfo1 = new OrderInfo("firstName1", "lastName1", "r3y0b6", "2046666666", "Mars");
      OrderInfo orderInfo2 = new OrderInfo("firstName2", "lastName2", "r3y0b7", "2046666667", "Heaven");
      final List<Order> orders1 = new ArrayList<>();
      final List<Order> orders2 = new ArrayList<>();
      orders1.add(new Order("book1", "Tianhua Xu", "Tianhua Xu1", 1,orderInfo1));
      orders2.add(new Order("book2", "Tianhua Xu2", "Tianhua Xu3", 11,orderInfo2));
      when(orderPersistence.getBuyerOrders("Tianhua Xu")).thenReturn(orders1);
      when(orderPersistence.getSellerOrders("Tianhua Xu")).thenReturn(orders2);

      accessOrders.buyerOrderHistory("Tianhua Xu");
      accessOrders.sellerOrderHistory("Tianhua Xu");
      verify(orderPersistence).getBuyerOrders("Tianhua Xu");
      verify(orderPersistence).getSellerOrders("Tianhua Xu");

      System.out.println("\nStarting test testInsertOrder\n");

    }


    @Test
    public void InsertOrder(){
      System.out.println("\nStarting test testInsertOrder\n");
      final List<Order> orders = new ArrayList<>();
      OrderInfo orderInfo1 = new OrderInfo("firstName1", "lastName1", "r3y0b6", "2046666666", "Mars");
      orders.add(new Order("book1", "Tianhua Xu", "Tianhua Xu1", 1,orderInfo1));
      final Order order=new Order("book2", "Tianhua Xu2", "Tianhua Xu3", 3,orderInfo1);

      Boolean result;

      when(orderPersistence.insertOrder(order)).thenReturn(order);

      result = accessOrders.insertOrder(order);
      assertTrue(result.equals(true));

      verify(orderPersistence).insertOrder(order);

      orderPersistence = new OrderPersistenceStub();
      accessOrders = new AccessOrders(orderPersistence);

      OrderInfo orderInfo3 = new OrderInfo("firstName3", "lastName3", "r3y0b7", "2046666664", "Heaves");
      Order newOrder = new Order("book3", "Tianhua Xu", "Tianhua Xu", 4,orderInfo3);

      accessOrders.insertOrder(newOrder);

      assertTrue(accessOrders.getOrder().size() == 3);
      System.out.println("\nStarting test testInsertOrder\n");
    }


}
