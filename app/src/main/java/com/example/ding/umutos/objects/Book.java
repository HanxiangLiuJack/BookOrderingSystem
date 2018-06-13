package com.example.ding.umutos.objects;

public class Book
{
    private int bookID;
    private String bookName;
    private String authorName;
    private int bookPicture;
    private String bookDescription;
    private String bookCategory;
    private int ownerID;
    private  double price;

    private static int index = 0;

    public Book(String bookName , String authorName , int bookPicture, String bookDescription, String bookCategory, double price, int ownerID)
    {
        //book id automated created
        bookID = index;
        index++;

        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPicture = bookPicture;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.price = price;
        this.ownerID = ownerID;
    }

    public void setPicture(int pic)
    {
        this.bookPicture = pic;
    }

    public int getPicture()
    {
        return bookPicture;
    }

    public String getDescription()
    {
        return bookDescription;
    }

    public void setDescription(String info)
    {
        this.bookDescription = info;
    }

    public String getCategory()
    {
        return this.bookCategory;
    }

    public void setCategory(String c)
    {
        this.bookCategory = c;
    }

    public void setName(String name)
    {
        this.bookName = name;
    }

    public String getName()
    {
        return bookName;
    }

    public void setAuthor(String author)
    {
        this.authorName = author;
    }

    public String getAuthor()
    {
        return authorName;
    }

    public void setPrice(double p)
    {
        this.price = p;
    }

    public double getPrice()
    {
        return price;
    }

    public int getBookID()
    {
        return bookID;
    }

    public int getOwner(){
        return ownerID;
    }
}
