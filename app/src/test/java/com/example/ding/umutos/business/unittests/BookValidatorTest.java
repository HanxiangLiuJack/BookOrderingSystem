package com.example.ding.umutos.business.unittests;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.business.BookValidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

public class BookValidatorTest {
   private Book validBook;
   private Book invalidBook;
   private BookValidator bookValidator;
   @Before
    public void setup(){
       validBook = new Book ("aaa","bbb",1,"ccc","ddd",5.99, "Tianhua Xu");
       invalidBook = new Book ("","",-1,"","",-1, "Tianhua Xu");
       bookValidator = new BookValidator();
   }
    @After
    public void tearDown(){
       validBook = null ; invalidBook = null;bookValidator = null ;
   }

    @Test
    public void testNotNull() {
        System.out.println("Test Not Null case.");
        assertNotNull(validBook);
        assertNotNull(invalidBook);
        System.out.println("Finish test not Null cases.");

    }
    @Test
    public void testBookName() {
        System.out.println("Test BookName case.");
        assertTrue(bookValidator.validateBookName(validBook.getName()));
        assertTrue(!bookValidator.validateBookName(invalidBook.getName()));
        System.out.println("Finish test BookName.");
    }

    @Test
    public void testAuthorName() {
        System.out.println("Test Author Name");
        assertTrue(bookValidator.validateAuthorName(validBook.getAuthor()));
        assertTrue(!bookValidator.validateAuthorName(invalidBook.getAuthor()));
        System.out.println("Finish Author Name");
    }

    @Test
    public void testBookPicture() {
        System.out.println("Test Book picture ");
        assertTrue(bookValidator.validateBookPictureIndex(validBook.getPicture()));
        assertTrue(!bookValidator.validateBookPictureIndex(invalidBook.getPicture()));
        System.out.println("Finish Book picture");
    }

    @Test
    public void testBookPirce() {
        System.out.println("Test Book price ");
        assertTrue(bookValidator.validatePrice(validBook.getPrice()));
        assertTrue(!bookValidator.validatePrice(invalidBook.getPrice()));
        System.out.println("Finish Book price ");
    }

    @Test
    public void testBook() {
        System.out.println("Test Book");
        assertTrue(bookValidator.validateBook(validBook));
        assertTrue(!bookValidator.validateBook(invalidBook));
        System.out.println("Finish Book");
    }
}
