package com.example.ding.umutos.objects;

/*
   Account object:
   1. userID, which is a final int cannot be changed or setted.
   2. userName, the user's name.
   3. passWord.
*/

public class Account
{
    private final int userID;
    private String userName;
    private String passWord;
   
    public Account( int userID , String userName ,String passWord)
    {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
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

    public void setPassWord(String p){passWord = p;}

    public String getPassWord(){return passWord;}
}
