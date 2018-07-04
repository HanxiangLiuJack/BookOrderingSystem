package com.example.ding.umutos.business;


import android.util.Log;

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

    public AccessBooks(final BookPersistence bookPersistence)
    {
        this();
        this.bookPersistence = bookPersistence;
    }

    public List<Book> getBooks()
    {
        books = bookPersistence.getBookSequential();

        return books;
    }

    public List<Book> getUserBooks(int userID)
    {
        userBooks = bookPersistence.getUserBookSequential(userID);
        return userBooks;
    }

    public boolean insertBook(Book currentBook)
    {
        BookValidator validator = new BookValidator();
        if(currentBook != null) {
            if(validator.validateBook(currentBook)) {
                bookPersistence.insertBook(currentBook);
                return true;
            }
        }

        return false;
    }


    public List<Book> CategoryList(String category){
        List<Book> cBook;
        if (category.equals("ALL")){
            cBook=getBooks();
        }
        else {
            cBook=bookPersistence.getBookCategorySequential(category);
        }
        return cBook;
    }


    public List<Book> ascentSort(){
        List<Book> sortBook;
        BookSorter sorter = new BookSorter();
        sortBook=sorter.LowPrice(getBooks());
        return sortBook;
    }

    public List<Book> declineSort(){
        List<Book> sortBook;
        BookSorter sorter=new BookSorter();
        sortBook=sorter.HighPrice(getBooks());
        return sortBook;
    }

    public Book searchBook(int id)
    {
        return bookPersistence.searchBook(id);
    }

    public List<Book> searchBooksByKeyWord(String key)
    {
        return bookPersistence.searchKeyword(key);
    }

    public boolean updateBook(Book currentBook)
    {
        BookValidator validator = new BookValidator();
        if(currentBook != null) {
            if(validator.validateBook(currentBook)) {
                bookPersistence.updateBook(currentBook);
                return true;
            }
        }
        return false;
    }

    public void deleteBook(int bookID)
    {
        if(searchBook(bookID) != null)
          bookPersistence.deleteBook(bookID);
    }
}
