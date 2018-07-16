package com.example.ding.umutos.objects;


public class Wish {
    private int bookID;
    private String authorName;
    private String userName;
    private String bookName;

    public Wish(int bookID, String userName, String authorName, String bookName)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.userName = userName;
        this.bookID=bookID;
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

    public String getUserName(){
        return userName;
    }

}
