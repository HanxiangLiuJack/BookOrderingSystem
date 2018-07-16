package com.example.ding.umutos.objects;


public class Item {
    private int bookID;
    private String bookName;
    private double price;
    private String userName;

    public Item(String userName, int bookID, String bookName, double price)
    {
        this.userName = userName;
        this.bookName = bookName;
        this.bookID = bookID;
        this.price = price;
    }

    public String getUserName()
    {
        return userName;
    }
    public String getName()
    {
        return bookName;
    }

    public int getBookID()
    {
        return bookID;
    }

    public double getPrice(){
        return price;
    }

}
