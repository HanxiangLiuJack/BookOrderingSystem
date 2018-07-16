package com.example.ding.umutos.objects;


public class Wish {
    private int bookID;
    private String authorName;
    private String userName;
    private String bookName;

    public Wish( String userName, String authorName, String bookName)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.userName = userName;
    }

    public String getName()
    {
        return bookName;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public int getBookID()
    {
        return bookID;
    }

    public void setBookID(int bookID) { this.bookID = bookID; }

    public String getUserName(){
        return userName;
    }

}
