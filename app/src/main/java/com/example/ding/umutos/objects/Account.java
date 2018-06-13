package com.example.ding.umutos.objects;

/*
   Account object:
   1. userID, which is a final int cannot be changed or setted.
   2. userName, the user's name.
*/

public class Account
{
    private final int userID;
    private String userName;
   
    public Account( int userID , String userName )
    {
        this.userID = userID;
        this.userName = userName;
    }
    
    public int getUserID()
    {
        return userID;
    }
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String newName)
    {
        userName = newName;
    }
}
