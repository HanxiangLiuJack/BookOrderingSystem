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
    private int ownerID;
    private  double price;
    private int[] bookImg;
    private String[] categoryArr;

    public Book(String bookName , String authorName , int bookPicture, String bookDescription, String bookCategory, double price, int ownerID)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPicture = bookPicture;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.price = price;
        this.ownerID = ownerID;
    }

    public Book(){
        bookImg =  new int[]{R.mipmap.book0, R.mipmap.book1, R.mipmap.book2, R.mipmap.book3, R.mipmap.book4, R.mipmap.book5, R.mipmap.book6, R.mipmap.book7, R.mipmap.book8, R.mipmap.book9, R.mipmap.book10, R.mipmap.book11, R.mipmap.book12, R.mipmap.book13, R.mipmap.book14, R.mipmap.book15, R.mipmap.book16, R.mipmap.book17, R.mipmap.book18, R.mipmap.book19, R.mipmap.book20};
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

    public int getOwner(){
        return ownerID;
    }

    public int getImageByBookID(int bookID){
        if(bookID>20)
            return bookImg[0];
        else
            return bookImg[bookID];
    }

    public String[] getCategoryArr(){
        return categoryArr;
    }


}
