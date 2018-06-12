package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


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
        System.out.println("\nStarting test testGetBookList\n");
        accessBooks = new AccessBooks();
        testBookList = accessBooks.getBooks();
        numOfBooks = testBookList.size();
        assertTrue(numOfBooks == 10); //there are 10 books in the stub database
        System.out.println("\nFinished testing testGetBookList\n");
    }

    @Test
    public void testGetUserBookList()
    {
        System.out.println("\nStarting test testGetUserBookList\n");
        accessBooks = new AccessBooks();
        testUserBookList = accessBooks.getUserBooks(1);
        numOfBooks = testUserBookList.size();
        assertTrue(numOfBooks == 2);//account 1 has 2 books
        System.out.println("\nFinished testing testGetUserBookList\n");
    }

    @Test
    public void testInsertBook()
    {
        System.out.println("\nStarting test testInsertBook\n");
        //test inserting invalid and valid books
        accessBooks = new AccessBooks();
        testInsertInvalidBook();
        testInsertValidBook();
        System.out.println("\nStarting test testInsertBook\n");
    }

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
        testHighInvalidPrice();
        testNullCategory();
        testEmptyCategory();
        testLongCategory();
    }

    private void testInsertValidBook()
    {
        templateBook = new Book( "name", "Author", 1, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) != null);
    }

    private void testNullBook()
    {
        templateBook = null;
        assertNull(accessBooks.insertBook(templateBook));
    }

    private void testNullBookName()
    {
        templateBook = new Book( null, "Author", 2, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testEmptyBookName()
    {
        templateBook = new Book( "", "Author", 2, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testLongBookName()
    {
        templateBook = new Book( "thisbook'snameislongerthanfiftycharactersaaaaaaaaaaaa",
                                 "Author", 2, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNullAuthorName()
    {
        templateBook = new Book( "name", null, 3, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testEmptyAuthorName()
    {
        templateBook = new Book( "name", "", 3, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testLongAuthorName()
    {
        templateBook = new Book( "name", "thisauthor'snameislongerthan20chars", 4, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNegativeBookPictureIndex()
    {
        templateBook = new Book( "name", "Author", -1, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNegativeInvalidPrice()
    {
        templateBook = new Book( "name", "author", 5, "info", "COMP", -10, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testHighInvalidPrice()
    {
        templateBook = new Book( "name", "author", 5, "info", "COMP", 100000, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testNullCategory()
    {
        templateBook = new Book( "name", "author", 5, "info", null, 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testEmptyCategory()  //should return false
    {
        templateBook = new Book( "name", "author", 5, "info", "", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    private void testLongCategory()  //should return false
    {
        templateBook = new Book( "name", "author", 5, "info", "thiscategoryislongerthan10chars", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook) == null);
    }

    @Test
    public void testUpdateBook()
    {
        System.out.println("\nStarting test testUpdateBook\n");
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", 5, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);

        String newName = "name1";
        String newAuthor = "author1";
        int newPic = 9;
        String newInfo = "info1";
        String newCategory = "COMP1";
        double newPrice = 200;

        Book newBook;
        newBook = accessBooks.updateBook(templateBook, newName, newAuthor, newPic, newInfo, newCategory, newPrice);

        assertTrue(newBook.getName().equals("name1"));
        assertTrue(newBook.getAuthor().equals("author1"));
        assertTrue(newBook.getPicture()==9);
        assertTrue(newBook.getDescription().equals("info1"));
        assertTrue(newBook.getCategory().equals("COMP1"));
        assertTrue(newBook.getPrice() == 200);
        accessBooks.deleteBook(templateBook.getBookID());
        System.out.println("\nStarting test testUpdateBook\n");
    }

    @Test
    public void testSearchBook()
    {
        System.out.println("\nStarting test testSearchBook\n");
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", 1, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);
        assertTrue(accessBooks.searchBook(templateBook.getBookID()).getBookID() == templateBook.getBookID());
        accessBooks.deleteBook(templateBook.getBookID());
        System.out.println("\nStarting test testSearchBook\n");
    }

    @Test
    public void testDeleteBook()
    {
        System.out.println("\nStarting test testDeleteBook\n");
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", 1, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);
        accessBooks.deleteBook(templateBook.getBookID());
        assertTrue(accessBooks.searchBook(templateBook.getBookID()) == null);
        System.out.println("\nStarting test testDeleteBook\n");
    }
}
