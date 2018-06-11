package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Book;
import java.util.List;

public interface  BookPersistence {
  
    List<Book> getBookSequential();

    Book insertBook(Book currentBook);

    Book updateBook(Book currentBook, String book_Name, String author_Name, int book_Picture, String book_Description, String book_Category, double price);
    
    Book searchBook(int id);
    
    List<Book> getUserBookSequential(int userID);
    
    void deleteBook(int id);
   
}