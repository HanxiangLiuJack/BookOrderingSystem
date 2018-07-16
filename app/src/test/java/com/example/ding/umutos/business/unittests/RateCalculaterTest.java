package com.example.ding.umutos.business.unittests;

import com.example.ding.umutos.business.RateCalculator;

import com.example.ding.umutos.objects.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
public class RateCalculaterTest {

    private RateCalculator rateCalculator;
    private Account account;

    @Before
    public void setup()
    {
        account = new Account("huahua" , "1111");
        rateCalculator = new RateCalculator();
    }

    @After
    public void tearDown()
    {
        account = null;
        rateCalculator = null;
    }

    @Test
    public void testCalculateRate()
    {
        System.out.println("\nStarting test testCalculateRate\n");
        account.setRate(rateCalculator.calculateRate(account, 5));
        assertTrue(account.getRate() == 5);
        account.setRate(rateCalculator.calculateRate(account, 4));
        assertTrue(account.getRate() == 4.5);
        account.setRate(rateCalculator.calculateRate(account, 3));
        assertTrue(account.getRate() == 4);
        account.setRate(rateCalculator.calculateRate(account, 2));
        assertTrue(account.getRate() == 3.5);
        System.out.println("\nEnd test testCalculateRate\n");
    }
}
