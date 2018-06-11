package com.example.ding.umutos.objects;

public class Account
{
    private final int userID;
    private String userName;
   
    public Account( int userID , String userName )
    {
        this.userID = userID;
        this.userName = userName;
    }
    
    public int getUserID(){return userID;}
    public String getUserName(){return userName;}

    public void setUserID(String newName){userName = newName;}

    
}
