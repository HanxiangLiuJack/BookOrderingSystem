package com.example.ding.umutos.objects;


public class Item {
    private int bookID;
    private String bookName;
    private double price;

    public Item(int bookID, String bookName, double price)
    {
        this.bookName = bookName;
        this.bookID = bookID;
        this.price = price;
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
