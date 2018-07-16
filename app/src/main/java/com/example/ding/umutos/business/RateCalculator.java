package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Account;
public class RateCalculator {

    public double calculateRate(Account seller, double rate)
    {
        int ratedPeople = seller.getRatedPerson();
        double oldRate = seller.getRate();
        double newRate;
        if(oldRate == 0)
            newRate = rate;
        else
            newRate = (oldRate*ratedPeople + rate)/(ratedPeople+1);
        seller.setRatedPerson(ratedPeople+1);
        return newRate;
    }
}
