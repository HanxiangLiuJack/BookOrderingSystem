package com.example.ding.umutos.objects;

import java.util.ArrayList;
import java.util.List;
/*
   Account object:
   1. userID, which is a final int cannot be changed or setted.
   2. userName, the user's name.
   3. passWord.
*/

public class Account
{
    private String userName;
    private String password;

    private double rate;
    private int ratedPerson;

    public Account( String userName ,String password)
    {
        this.userName = userName;
        this.password = password;
        ratedPerson = 0;
        rate = 0;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String newName)
    {
        userName = newName;
    }

    public String getPassword()
    {
        return password;
    }

    public double getRate(){
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public int getRatedPerson()
    {
        return ratedPerson;
    }

    public void setRatedPerson(int num)
    {
        ratedPerson = num;
    }

}
