package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Account;
public class RateCalculator {

    public double calculateRate(Account seller, double rate)
    {
        int ratedPeople = seller.getRatedPerson();
        double newRate = (seller.getRate()*ratedPeople + rate)/(ratedPeople+1);
        seller.setRatedPerson(ratedPeople+1);
        return newRate;
    }
}
