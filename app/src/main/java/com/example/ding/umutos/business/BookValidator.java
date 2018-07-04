package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Book;

public class BookValidator {

    public boolean validateBook(Book book)
    {
        return validateBookName(book.getName()) && validateAuthorName(book.getAuthor()) &&
                validateBookPictureIndex(book.getPicture()) && validatePrice(book.getPrice());
    }

    private boolean validateBookName(String bookName)
    {
        //book name shouldn't be null, empty string, spaces or longer than 30 chars
        return bookName != null && !bookName.equals("") && !bookName.trim().isEmpty() && bookName.length()<=60;
    }

    private boolean validateAuthorName(String authorName)
    {
        //same standard as book name
        return authorName != null && !authorName.equals("") && !authorName.trim().isEmpty() && authorName.length()<=30;
    }

    private boolean validateBookPictureIndex(int index)
    {
        return index>=0;
    }

    private boolean validatePrice(Double price)
    {
        return  price >= 0;
    }
}
