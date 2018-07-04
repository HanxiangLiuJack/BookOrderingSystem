package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Book;

import com.example.ding.umutos.persistence.BookPersistence;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;



public class AccessBooksTest {

    private AccessBooks accessBooks;
    private BookPersistence bookPersistence;

    @Before
    public void setup()
    {
        bookPersistence = mock(BookPersistence.class);
        accessBooks = new AccessBooks(bookPersistence);
    }

    @After
    public void tearDown()
    {
        accessBooks = null;
    }

    @Test
    public void testNullBookAccess()
    {

        System.out.println("\nStarting test null book access.\n");
        assertNotNull(accessBooks);
        System.out.println("\nFinish testing null book access.\n");
    }

    @Test
    public void testGetBookList()
    {

        System.out.println("\nStarting test testGetBookList\n");
        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));

        when(bookPersistence.getBookSequential()).thenReturn(books);


        List<Book> list = accessBooks.getBooks();

        assertTrue(list.equals(books));

        verify(bookPersistence).getBookSequential();
        System.out.println("\nFinished testing testGetBookList\n");

    }

    @Test
    public void testGetUserBookList()
    {

        System.out.println("\nStarting test testGetUserBookList\n");
        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        books.add(new Book("bbb","ccc",2,"eee","fff",5.99,1));

        when(bookPersistence.getUserBookSequential(1)).thenReturn(books);

        List<Book> temp = accessBooks.getUserBooks(1);

        assertTrue(temp.equals(books));

        verify(bookPersistence).getUserBookSequential(1);

        System.out.println("\nFinished testing testGetUserBookList\n");

    }

    @Test
    public void testInsertBook()
    {
        System.out.println("\nStarting test testInsertBook\n");

        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        final Book book = new Book("bbb","ccc",2,"eee","fff",5.99,1);
        Boolean result ;

        when(bookPersistence.insertBook(book)).thenReturn(book);

        result = accessBooks.insertBook(book);
        assertTrue(result.equals(true));

        verify(bookPersistence).insertBook(book);

        System.out.println("\nStarting test testInsertBook\n");
    }


    @Test
    public void testSearchBook()
    {
        System.out.println("\nStarting test testSearchBook\n");
        final Book book = new Book("aaa","bbb",1,"ddd","eee",9.99,1);
        when(bookPersistence.searchBook(1)).thenReturn(book);

        Book temp = accessBooks.searchBook(1);

        assertTrue(temp.equals(book));

        verify(bookPersistence).searchBook(1);

        System.out.println("\nStarting test testSearchBook\n");
    }

    @Test
    public void testSearchBookByKeyWord()
    {
        System.out.println("\nStarting test testSearchBookByKeyWord\n");
        final List <Book> bookList = new ArrayList<>();
        bookList.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        String key = "aaa";
        when(bookPersistence.searchKeyword(key)).thenReturn(bookList);
        List <Book> temp = accessBooks.searchBooksByKeyWord(key);
        assertTrue(temp.equals(bookList));
        verify(bookPersistence).searchKeyword(key);
        System.out.println("\nFinishing test testSearchBookByKeyWord\n");
    }

    @Test
    public void testDeleteBook()
    {
        System.out.println("\nStarting test testDeleteBook\n");
        final Book book = new Book("aaa","bbb",1,"ddd","eee",9.99, 1);

        when(bookPersistence.searchBook(1)).thenReturn(book);

        doNothing().when(bookPersistence).deleteBook(1);

        accessBooks.deleteBook(1);

        verify(bookPersistence).searchBook(1);
        verify(bookPersistence).deleteBook(1);
        System.out.println("\nFinishing test testDeleteBook\n");
    }

}
