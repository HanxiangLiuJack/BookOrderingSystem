package com.example.ding.umutos.objects;

/*
   Account object:
   1. userID, which is a final int cannot be changed or setted.
   2. userName, the user's name.
*/

public class Account
{
    private int userID;
    private String userName;
    private String password;
   
    public Account( String userName, String password )
    {
        this.userName = userName;
        this.password = password;
    }
    
    public int getUserID()
    {
        return userID;
    }
    public String getUserName()
    {
        return userName;
    }
    public String getPassword() { return password; }

    public void setUserID(int newUserID){ userID = newUserID; }
    public void setUserName(String newName)
    {
        userName = newName;
    }
    public void setPassword(String newpassword) { password = newpassword; }

}
