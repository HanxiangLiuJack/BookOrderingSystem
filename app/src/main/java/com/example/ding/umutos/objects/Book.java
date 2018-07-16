package com.example.ding.umutos.objects;

/*
  Book object:
  1. bookID, an int indicates the book's ID
  2. bookName, book's name
  3. authorName, book author's name 
  4. bookPicture, book's picture
  5. bookDescription, description of a book
  6. bookCategory, book's category.
  7. ownerID, book's owner's userID
  8. price, the price of the book.
*/

import com.example.ding.umutos.R;

public class Book
{
    private int bookID;
    private String bookName;
    private String authorName;
    private int bookPicture;
    private String bookDescription;
    private String bookCategory;
    private String ownerName;
    private  double price;
    private String[] categoryArr;

    public Book(String bookName , String authorName , int bookPicture, String bookDescription, String bookCategory, double price, String ownerName)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPicture = bookPicture;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.price = price;
        this.ownerName = ownerName;
    }

    public Book(){
         categoryArr = new String[]{"ALL","Agriculture", "Architecture and design", "Business","Biology", "Computer Science","Divinity", "Education", "Engineering and technology", "Environmental studies and forestry", "Family and consumer science", "Human physical performance and recreation", "Journalism, media studies and communication", "Law", "Library and museum studies", "Medicine", "Military sciences", "Public administration", "Public policy", "Social work", "Transportation"};
    }

    public int getPicture()
    {
        return bookPicture;
    }

    public void setPicture(int pic)
    {
        this.bookPicture = pic;
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

    public String getName()
    {
        return bookName;
    }

    public void setName(String name)
    {
        this.bookName = name;
    }

    public String getAuthor()
    {
        return authorName;
    }

    public void setAuthor(String author)
    {
        this.authorName = author;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double p)
    {
        this.price = p;
    }

    public int getBookID()
    {
        return bookID;
    }

    public void setBookID(int bookID)
    {
        this.bookID = bookID;
    }

    public String getOwner(){
        return ownerName;
    }

    public String[] getCategoryArr(){
        return categoryArr;
    }


}
