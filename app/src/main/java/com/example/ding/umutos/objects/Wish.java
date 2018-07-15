package com.example.ding.umutos.objects;


public class Wish {
    private int bookID;
    private double price;
    private String ownerName;
    private String bookName;

    public Wish(int bookID, double price, String ownerName, String bookName)
    {
        this.bookName = bookName;
        this.price = price;
        this.ownerName = ownerName;
        this.bookID=bookID;
    }

    public void setPrice(double p)
    {
        this.price = p;
    }

    public void setBookID(int bookID)
    {
        this.bookID = bookID;
    }

    public void setName(String name)
    {
        this.bookName = name;
    }

    public void setOwnerName(String name){this.ownerName=name;}


}
