package com.example.ding.umutos.business.integrationtests;

import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.utils.TestUtils;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.example.ding.umutos.business.AccessBooks;

public class AccessBooksIT {
        private AccessBooks accessBooks;
        private File tempDB;


        @Before
        public void setUp() throws IOException {
            this.tempDB = TestUtils.copyDB();
            this.accessBooks = new AccessBooks();
        }

        @Test
        public void testListBooks() {
            final Book book;
            book = accessBooks.getBooks().get(0);
            assertNotNull("first sequential course should not be null", book);
            assertTrue("Agile Development".equals(book.getName()));
            assertTrue("James Shore".equals(book.getAuthor()));
            assertTrue("Tianhua Xu".equals(book.getOwner()));
            assertTrue(33.86==book.getPrice());
            assertTrue("Family and consumer science".equals(book.getCategory()));
            assertTrue(1==book.getBookID());
            assertTrue("The Art of Agile Development contains practical guidance for anyone considering or applying agile development for building valuable software. Plenty of books describe what agile development is or why it helps software projects succeed, but very few combine information for developers, managers, testers, and customers into a single package that they can apply directly.".equals(book.getDescription()));
            System.out.println("Finished test AccessBooks");
        }

        @Test
        public void testInsertBook(){
            Book newBook=new Book("a","ab",2,"abc","abcd",1.44,"abcde");
            assertTrue(accessBooks.insertBook(newBook));
            assertTrue(21==accessBooks.getBooks().size());
            System.out.println("Finished test AccessBooks");
        }

        @Test
        public void deleteBook(){
            accessBooks.deleteBook(1);
            assertTrue(19==accessBooks.getBooks().size());
            System.out.println("Finished test AccessBooks");
        }

        @Test
        public void getBook(){
            assertNotNull("first sequential course should not be null", accessBooks.getBooks());
            System.out.println("Finished test AccessBooks");
        }

        @Test
        public void searchBooksByKeyWord() {
            Book newBook=new Book("aaa","bbb",1,"ddd","eee",9.99, "Tianhua Xu");
            String key = "aaa";
            assertTrue(accessBooks.insertBook(newBook));
            assertTrue(accessBooks.searchBooksByKeyWord(key).get(0).getName().equals(newBook.getName()));
            System.out.println("Finished test AccessBooks");
        }

        @After
        public void tearDown() {
            // reset DB
            TestUtils.delete();
            accessBooks=null;
        }


    }
