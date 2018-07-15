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

    public String getName()
    {
        return bookName;
    }

    public double getPrice()
    {
        return price;
    }

    public int getBookID()
    {
        return bookID;
    }

    public String getOwner(){
        return ownerName;
    }
    

}
