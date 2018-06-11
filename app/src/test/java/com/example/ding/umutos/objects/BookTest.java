package com.example.ding.umutos;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookTest {
    @Test
    public void testBook()
    {
            Book book;
            System.out.println("Testing book");
            book = new Book("abc","Amy",123,"a good book","CS",24.99,12345);
            
            assertNotNull(book);
            
            assertTrue("abc".equals(book.getName()));
            assertTrue("Amy".equals(book.getAuthor()));
            assertTrue(123.equals(book.getPicture()));
            assertTrue("a good book".equals(book.getDescription()));
            assertTrue("CS".equals(book.getCategory()));
            assertTrue(24.99 == book.getPrice());
            assertTrue(12345 == book.getOwner());
            System.out.println("Getter cases passed ~");
            
            book.setName("cde");
            assertTrue("cde".equals(book.getName()));
            book.setAuthor("Bob");
            assertTrue("Bob".equals(book.getAuthor()));
            book.setPicture(222);
            assertTrue(222.equals(book.getPicture()));
            book.setDescription("a bad book");
            assertTrue("a bad book".equals(book.getDescription()));
            book.setCategory("Math");
            assertTrue("Math".equals(book.getCategory()));
            book.setPrice(28.99);
            assertTrue(28.99 == book.getPrice());
            System.out.println("Setter cases passed ~");
            
            book.setPicture(0);
            assertTrue(0.equals(book.getPicture()));
            book.setAuthor("");
            assertTrue("".equals(book.getAuthor()));
            book.setName("");
            assertTrue("".equals(book.getName()));
            book.setDescription("");
            assertTrue("".equals(book.getDescription()));
            book.setCategory("");
            assertTrue("".equals(book.getCategory()));
            book.setPrice(0.0);
            assertTrue(0.0 == book.getPrice());
            System.out.println("Empty cases passed ~");
            
            System.out.println("Tests done");
    }
}
