package com.example.ding.umutos.objects;

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
   
    public Account( String userName ,String password)
    {
        this.userName = userName;
        this.password = password;
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



}
