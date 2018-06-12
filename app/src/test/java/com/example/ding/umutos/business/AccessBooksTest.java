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
        //leave here empty
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
        testUserBookList = accessBooks.getUserBooks(2);
        numOfBooks = testUserBookList.size();
        assertTrue(numOfBooks == 5);//account 1 has 2 books
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
    }

    private void testInsertValidBook()
    {
        templateBook = new Book( "name", "Author", 1, "info", "COMP", 100, 1 );
        assertTrue(accessBooks.insertBook(templateBook));
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
        templateBook = new Book( "thisbook'snameislongerthanfiftycharactersaaaaaaaaaaaa",
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
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", 5, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);

        //test update valid message
        String newName = "name1";
        String newAuthor = "author1";
        int newPic = 9;
        String newInfo = "info1";
        String newCategory = "COMP1";
        double newPrice = 200;

        boolean updatedOrNot = accessBooks.updateBook(templateBook, newName, newAuthor, newPic, newInfo, newCategory, newPrice);

        assertTrue(updatedOrNot);
        assertTrue(templateBook.getName().equals("name1"));
        assertTrue(templateBook.getAuthor().equals("author1"));
        assertTrue(templateBook.getPicture()==9);
        assertTrue(templateBook.getDescription().equals("info1"));
        assertTrue(templateBook.getCategory().equals("COMP1"));
        assertTrue(templateBook.getPrice() == 200);

        //test update invalid message
        newName = "";
        newAuthor = "";
        newPic = -1;
        newPrice = -100;

        updatedOrNot = accessBooks.updateBook(templateBook, newName, newAuthor, newPic, newInfo, newCategory, newPrice);
        assertTrue(!updatedOrNot);

        accessBooks.deleteBook(templateBook.getBookID());
        System.out.println("\nEnd testing testUpdateBook\n");
    }

    @Test
    public void testSearchBook()
    {
        System.out.println("\nStarting test testSearchBook\n");
        accessBooks = new AccessBooks();
        templateBook = new Book("name", "author", 1, "info", "COMP", 100, 1);
        accessBooks.insertBook(templateBook);

        //search a book exist in the list
        assertTrue(accessBooks.searchBook(templateBook.getBookID()).getBookID() == templateBook.getBookID());
        accessBooks.deleteBook(templateBook.getBookID());

        //search a book not exist
        assertNull(accessBooks.searchBook(100));
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
