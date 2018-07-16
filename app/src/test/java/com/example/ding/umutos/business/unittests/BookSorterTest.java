package com.example.ding.umutos.business.unittests;

import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.BookPersistenceStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.presentation.BookSorter;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class BookSorterTest {

    private AccessBooks accessBooks;

    @Before
    public void setup() {
        accessBooks = new AccessBooks(new BookPersistenceStub());
    }

    @After
    public void tearDown() {
        accessBooks = null;
    }

    @Test
    public void testAscendingSort() {
        assertNotNull(accessBooks);

        BookSorter newSorter = new BookSorter();

        List<Book> afterSort = newSorter.LowPrice(accessBooks.getBooks());

        assertTrue(afterSort.size() == 10);
        assertTrue(afterSort.get(0).getPrice() == 9.95);
        assertTrue(afterSort.get(1).getPrice() == 11.12);
        assertTrue(afterSort.get(8).getPrice() == 34.95);
        assertTrue(afterSort.get(9).getPrice() == 163.34);
    }

    @Test
    public void testDescendingSort() {
        BookSorter newSorter = new BookSorter();

        List<Book> afterSort = newSorter.HighPrice(accessBooks.getBooks());
        assertTrue(afterSort.size() == 10);
        assertTrue(afterSort.get(0).getPrice() == 163.34);
        assertTrue(afterSort.get(1).getPrice() == 34.95);
        assertTrue(afterSort.get(8).getPrice() == 11.12);
        assertTrue(afterSort.get(9).getPrice() == 9.95);
    }
}
