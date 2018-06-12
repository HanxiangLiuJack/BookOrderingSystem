package com.example.ding.umutos.business;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.BookPersistence;

public class AccessBooks {

    private BookPersistence bookPersistence;
    private List<Book> books;
    private List<Book> userBooks;

    public AccessBooks()
    {
        bookPersistence = Service.getBookPersistence();
        books = null;
    }

    public List<Book> getBooks()
    {
        books = bookPersistence.getBookSequential();
        return Collections.unmodifiableList(books);
    }

    public List<Book> getUserBooks(int userID)
    {
        userBooks = bookPersistence.getUserBookSequential(userID);
        return Collections.unmodifiableList(userBooks);
    }



    //===============================================================================================
    //===================================book insertion part=========================================
    //===============================================================================================

    public boolean insertBook(Book currentBook)
    {
        if(currentBook != null) {
            if(validateBook(currentBook)) {
                bookPersistence.insertBook(currentBook);
                return true;
            }
        }

        return false;
    }

    private boolean validateBook(Book book)
    {
        return validateBookName(book.getName()) && validateAuthorName(book.getAuthor()) &&
                validateBookPictureIndex(book.getPicture()) && validatePrice(book.getPrice());
    }

    private boolean validateBookName(String bookName)
    {
        //book name shouldn't be null, empty string, spaces or longer than 30 chars
        return bookName != null && !bookName.equals("") && !bookName.trim().isEmpty() && bookName.length()<=30;
    }

    private boolean validateAuthorName(String authorName)
    {
        //same standard as book name
        return authorName != null && !authorName.equals("") && !authorName.trim().isEmpty() && authorName.length()<=20;
    }

    private boolean validateBookPictureIndex(int index)
    {
        return index>=0;
    }

    private boolean validatePrice(double price)
    {
        return price >= 0;
    }


    //===============================================================================================
    //===============================================================================================
    //===============================================================================================

    public Book searchBook(int id)
    {
        return bookPersistence.searchBook(id);
    }

    public boolean updateBook(Book currentBook, String bookName, String authorName, int bookPic, String bookDescription, String category, double price)
    {
        if(validateBookName(bookName)&&validateAuthorName(authorName)&&validateBookPictureIndex(bookPic)&&validatePrice(price)) {
            bookPersistence.updateBook(currentBook, bookName, authorName, bookPic, bookDescription, category, price);
            return true;
        }
        return false;
    }

    public void deleteBook(int bookID)
    {
        if(searchBook(bookID) != null)
          bookPersistence.deleteBook(bookID);
    }
}
