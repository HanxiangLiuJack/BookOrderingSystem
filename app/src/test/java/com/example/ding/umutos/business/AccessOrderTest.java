package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.persistence.OrderPersistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessOrderTest {
  private AccessOrders accessOrders;
  private OrderPersistence orderPersistence;

  @Before
    public void setup()
  {
      orderPersistence = mock(OrderPersistence.class);
      accessOrders = new AccessOrders(orderPersistence);
  }


    @After
    public void tearDown(){ accessOrders = null ; orderPersistence = null ;}

    @Test
    public void testOrderHistory()
    {
        //
    }
}
