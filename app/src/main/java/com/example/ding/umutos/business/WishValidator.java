package com.example.ding.umutos.business;
public class WishValidator {

    public boolean validateWish(String bookName, String authorName)
    {
        return validateBookName(bookName)&&validateAuthorName(authorName);
    }

    public boolean validateBookName(String bookName)
    {
        return bookName.length() > 0;
    }

    public boolean validateAuthorName(String authorName)
    {
        return authorName.length() > 0;
    }
}
