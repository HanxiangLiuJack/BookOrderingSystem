package com.example.ding.umutos.business;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.BookPersistence;

import static junit.framework.Assert.*;


public class AccessBooksTest {

    private AccessBooks accessBooks;
    private List<Book> testBookList;
    private List<Book> testUserBookList;
    private int numOfBooks;
    private Book templateBook;

    @Before
    public void setup()
    {

    }

    @After
    public void tearDown()
    {
        accessBooks = null;
    }

    @Test
    public void testNullBookAccess()
    {
        accessBooks = new AccessBooks();
        System.out.println("\nStarting test null book access.\n");
        assertNotNull(accessBooks);
        System.out.println("\nFinish testing null book access.\n");
    }

    @Test
    public void testGetBookList()
    {
        accessBooks = new AccessBooks();
        testBookList = accessBooks.getBooks();
        numOfBooks = testBookList.size();
        assertTrue(numOfBooks == 10); //there are 10 books in the stub database
    }

    @Test
    public void testGetUserBookList()
    {
        accessBooks = new AccessBooks();
        testUserBookList = accessBooks.getUserBooks(1);
        numOfBooks = testUserBookList.size();
        assertTrue(numOfBooks == 2);//account 1 has 2 books
    }

    @Test
    public void testInsertBook()
    {
        accessBooks = new AccessBooks();
        testInsertInvalidBook();
        testInsertValidBook();
    }

    private void testInsertInvalidBook()
    {
        testNullBook();
        testNullBookName();
        testEmptyBookName();
        testLongBookName();
        testNullAuthorName();
        testEmptyAuthorName();
        testLongAuthorName();
        testNegativeInvalidPrice();
        testHighInvalidPrice();
        testNullCategory();
        testEmptyCategory();
        testLongCategory();
    }

    private void testInsertValidBook()
    {
        templateBook = new Book( "name", "Author", "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) != null);
    }

    private void testNullBook()
    {
        templateBook = null;
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNullBookName()
    {
        templateBook = new Book( null, "Author", "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testEmptyBookName()
    {
        templateBook = new Book( "", "Author", "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testLongBookName()
    {
        templateBook = new Book( "thisbook'snameislongerthanfiftycharactersaaaaaaaaaaaa",
                                 "Author", "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNullAuthorName()
    {
        templateBook = new Book( "name", null, "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testEmptyAuthorName()
    {
        templateBook = new Book( "name", "", "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testLongAuthorName()
    {
        templateBook = new Book( "name", "thisauthor'snameislongerthan20chars", "pic", "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNegativeInvalidPrice()
    {
        templateBook = new Book( "name", "author", "pic", "info", "COMP", -10, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testHighInvalidPrice()
    {
        templateBook = new Book( "name", "author", "pic", "info", "COMP", 100000, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNullCategory()
    {
        templateBook = new Book( "name", "author", "pic", "info", null, 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testEmptyCategory()  //should return false
    {
        templateBook = new Book( "name", "author", "pic", "info", "", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testLongCategory()  //should return false
    {
        templateBook = new Book( "name", "author", "pic", "info", "thiscategoryislongerthan10chars", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    @Test
    public void testUpdateBook()
    {
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", "pic", "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);

        String newName = "name1";
        String newAuthor = "author1";
        String newPic = "pic1";
        String newInfo = "info1";
        String newCategory = "COMP1";
        double newPrice = 200;

        Book newBook;
        newBook = accessBooks.updateBook(templateBook, newName, newAuthor, newPic, newInfo, newCategory, newPrice);

        assertTrue(newBook.getName().equals("name1"));
        assertTrue(newBook.getAuthor().equals("author1"));
        assertTrue(newBook.getPicture().equals("pic1"));
        assertTrue(newBook.getDescription().equals("info1"));
        assertTrue(newBook.getCategory().equals("COMP1"));
        assertTrue(newBook.getPrice() == 200);
        accessBooks.deleteBook(templateBook.getBookID());
    }

    @Test
    public void testSearchBook()
    {
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", "pic", "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);
        assertTrue(accessBooks.searchBook(templateBook.getBookID()).getBookID() == templateBook.getBookID());
        accessBooks.deleteBook(templateBook.getBookID());
    }

    @Test
    public void testDeleteBook()
    {
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", "pic", "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);
        accessBooks.deleteBook(templateBook.getBookID());
        assertTrue(accessBooks.searchBook(templateBook.getBookID()) == null);
    }
}
