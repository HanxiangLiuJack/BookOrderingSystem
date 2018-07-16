package com.example.ding.umutos.objects;

import com.example.ding.umutos.objects.Book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BookTest {

    private Book templateBook;

    @Before
    public void setup() {
        templateBook = new Book("abc","Amy",123,"a good book","CS",24.99,"Tianhua Xu");
    }

    @After
    public void tearDown() {
        templateBook = null;
    }

    @Test
    public void testNullBook() {
        System.out.println("Testing Null book");
        assertNotNull(templateBook);
        System.out.println("End Testing Null book");
    }

    @Test
    public void testAccessorMethods() {
        System.out.println("Testing get methods");
        assertTrue("abc".equals(templateBook.getName()));
        assertTrue("Amy".equals(templateBook.getAuthor()));
        assertTrue(123 == templateBook.getPicture());
        assertTrue("a good book".equals(templateBook.getDescription()));
        assertTrue("CS".equals(templateBook.getCategory()));
        assertTrue(24.99 == templateBook.getPrice());
        assertTrue(templateBook.getOwner().equals("Tianhua Xu"));
        System.out.println("End Testing get methods");
    }

    @Test
    public void testMutatorMethods() {
        System.out.println("Testing set methods");
        templateBook.setName("cde");
        assertTrue("cde".equals(templateBook.getName()));

        templateBook.setAuthor("Bob");
        assertTrue("Bob".equals(templateBook.getAuthor()));

        templateBook.setPicture(222);
        assertTrue(222 == (templateBook.getPicture()));

        templateBook.setDescription("a bad book");
        assertTrue("a bad book".equals(templateBook.getDescription()));

        templateBook.setCategory("Math");
        assertTrue("Math".equals(templateBook.getCategory()));

        templateBook.setPrice(28.99);
        assertTrue(28.99 == templateBook.getPrice());

        System.out.println("End testing set methods");
    }

    @Test
    public void testEmptyCases() {
        System.out.println("start Testing empty cases");
        templateBook.setPicture(0);
        assertTrue(0==(templateBook.getPicture()));
        templateBook.setAuthor("");
        assertTrue("".equals(templateBook.getAuthor()));
        templateBook.setName("");
        assertTrue("".equals(templateBook.getName()));
        templateBook.setDescription("");
        assertTrue("".equals(templateBook.getDescription()));
        templateBook.setCategory("");
        assertTrue("".equals(templateBook.getCategory()));
        templateBook.setPrice(0.0);
        assertTrue(0.0 == templateBook.getPrice());
        System.out.println("End Testing empty cases");
    }
}
