package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.BookPersistence;
import com.example.ding.umutos.persistence.BookPersistenceStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/*

 final List<Book> books = new ArrayList<>();
 books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));

 when(bookPersistence.getBookSequential()).thenReturn(books);

 verify(bookPersistence).getBookSequential();


*/


public class AccessBooksTest {
/*
    private AccessBooks accessBooks;
    private List<Book> testBookList;
    private List<Book> testUserBookList;
    private int numOfBooks;
    private Book templateBook;
    */

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
        /*
        System.out.println("\nStarting test testGetBookList\n");
        testBookList = accessBooks.getBooks();
        numOfBooks = testBookList.size();
        assertTrue(numOfBooks == 10); //there are 10 books in the stub database
        System.out.println("\nFinished testing testGetBookList\n");
        */
        System.out.println("\nStarting test testGetBookList\n");
        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        List <Book> list;
        when(bookPersistence.getBookSequential()).thenReturn(books);

        list = accessBooks.getBooks();
        assertTrue(list.equals(books));

        verify(bookPersistence).getBookSequential();
        System.out.println("\nFinished testing testGetBookList\n");

    }

    @Test
    public void testGetUserBookList()
    {

        /*
        System.out.println("\nStarting test testGetUserBookList\n");
        testUserBookList = accessBooks.getUserBooks(2);
        numOfBooks = testUserBookList.size();
        assertTrue(numOfBooks == 5);//account 1 has 2 books
        System.out.println("\nFinished testing testGetUserBookList\n");
        */
        System.out.println("\nStarting test testGetUserBookList\n");
        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        books.add(new Book("bbb","ccc",2,"eee","fff",5.99,1));

        List<Book>temp;
        when(bookPersistence.getBookSequential()).thenReturn(books);

        temp = accessBooks.getUserBooks(1);

        assertTrue(temp.equals(books));

        verify(bookPersistence).getUserBookSequential(1);
        verify(bookPersistence).getBookSequential();

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

        when(bookPersistence.getBookSequential()).thenReturn(books);

        result = accessBooks.insertBook(book);
        assertTrue(result.equals(true));

        verify(bookPersistence).getUserBookSequential(1);
        verify(bookPersistence).getBookSequential();

        System.out.println("\nStarting test testInsertBook\n");
    }


   /*
    private void testInsertInvalidBook()
    {
        //different types of invalid book
        testNullBook();
        testNullBookName();
        testEmptyBookName();
        testLongBookName();
        testNullAuthorName();
        testEmptyAuthorName();
        testLongAuthorName();
        testNegativeBookPictureIndex();
        testNegativeInvalidPrice();
    }

    private void testInsertValidBook()
    {
        templateBook = new Book( "name", "Author", 1, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook));
        accessBooks.deleteBook(templateBook.getBookID());
    }

    private void testNullBook()
    {
        templateBook = null;
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testNullBookName()
    {
        templateBook = new Book( null, "Author", 2, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testEmptyBookName()
    {
        templateBook = new Book( "", "Author", 2, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testLongBookName()
    {
        templateBook = new Book( "thisbook'snameisladusahodansiancisoaudfgaoudcbadouaboudcbaodubapefhuacboaducbaodubavodudongerthanfiftycharactersaaaaaaaaaaaa",
                                 "Author", 2, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testNullAuthorName()
    {
        templateBook = new Book( "name", null, 3, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testEmptyAuthorName()
    {
        templateBook = new Book( "name", "", 3, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testLongAuthorName()
    {
        templateBook = new Book( "name", "thisauthor'snameislongerthan20chars", 4, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testNegativeBookPictureIndex()
    {
        templateBook = new Book( "name", "Author", -1, "info", "COMP", 100, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    private void testNegativeInvalidPrice()
    {
        templateBook = new Book( "name", "author", 5, "info", "COMP", -10, 1 );
        assertTrue(!accessBooks.insertBook(templateBook));
    }

    @Test
    public void testUpdateBook()
    {
        System.out.println("\nStarting test testUpdateBook\n");
        templateBook = new Book("name", "author", 5, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);

        //test update valid message
        templateBook.setName("name1");
        templateBook.setAuthor("author1");
        templateBook.setPicture(9);
        templateBook.setDescription("info1");
        templateBook.setCategory("COMP1");
        templateBook.setPrice(200);

        boolean updatedOrNot = accessBooks.updateBook(templateBook);

        assertTrue(updatedOrNot);
        assertTrue(templateBook.getName().equals("name1"));
        assertTrue(templateBook.getAuthor().equals("author1"));
        assertTrue(templateBook.getPicture()==9);
        assertTrue(templateBook.getDescription().equals("info1"));
        assertTrue(templateBook.getCategory().equals("COMP1"));
        assertTrue(templateBook.getPrice() == 200);

        //test update invalid message
        templateBook.setName("");
        templateBook.setAuthor("");
        templateBook.setPicture(-1);
        templateBook.setPrice(-100);

        updatedOrNot = accessBooks.updateBook(templateBook);
        assertTrue(!updatedOrNot);

        accessBooks.deleteBook(templateBook.getBookID());
        System.out.println("\nEnd testing testUpdateBook\n");
    }*/

    @Test
    public void testSearchBook()
    {
        /*
        System.out.println("\nStarting test testSearchBook\n");
        templateBook = new Book("name", "author", 1, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);

        //search a book exist in the list
        assertTrue(accessBooks.searchBook(templateBook.getBookID()).getBookID() == templateBook.getBookID());
        accessBooks.deleteBook(templateBook.getBookID());

        //search a book not exist
        assertNull(accessBooks.searchBook(100));
        System.out.println("\nStarting test testSearchBook\n");
        */
        System.out.println("\nStarting test testSearchBook\n");


        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        //final Book book = new Book("aaa","bbb",1,"ddd","eee",9.99,1);
        List<Book>temp;
        when(bookPersistence.getBookSequential()).thenReturn(books);

        temp = accessBooks.getUserBooks(1);
        assertTrue(temp.equals(books));


        verify(bookPersistence).getUserBookSequential(1);
        verify(bookPersistence).getBookSequential();



        System.out.println("\nStarting test testSearchBook\n");
    }

    @Test
    public void testDeleteBook()
    {
        /*
        System.out.println("\nStarting test testDeleteBook\n");
        templateBook = new Book("name", "author", 1, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);
        accessBooks.deleteBook(templateBook.getBookID());
        assertTrue(accessBooks.searchBook(templateBook.getBookID()) == null);
        System.out.println("\nStarting test testDeleteBook\n");
        */
        System.out.println("\nStarting test testDeleteBook\n");

        final List<Book> books = new ArrayList<>();
        books.add(new Book("aaa","bbb",1,"ddd","eee",9.99, 1));
        books.add(new Book("bbb","eee",2, "fff","ggg",5.99,2));
        final Book book = new Book("aaa","bbb",1,"ddd","eee",9.99,1);

        Boolean result ;
        when(bookPersistence.getBookSequential()).thenReturn(books);

        accessBooks.deleteBook(1);

        assertTrue(accessBooks.searchBook(1).equals(false));

        verify(bookPersistence).getBookSequential();

        System.out.println("\nStarting test testDeleteBook\n");
    }

}
