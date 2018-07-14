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
    private int userID;
    private String userName;
    private String password;

    private List<String> comment;
    private double rate;
    private int ratedPerson;

    public Account( String userName ,String password)
    {
        this.userName = userName;
        this.password = password;
        comment = new ArrayList<String>();
        ratedPerson = 0;
    }
    
    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int newUserID)
    {
        userID = newUserID;
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

    public void setPassWord(String newPassword)
    {
        password = newPassword;
    }

    public List<String> getComment()
    {
        return comment;
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
