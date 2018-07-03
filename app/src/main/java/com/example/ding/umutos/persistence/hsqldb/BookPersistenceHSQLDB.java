package com.example.ding.umutos.persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.BookPersistence;

public class BookPersistenceHSQLDB implements BookPersistence {

    private final Connection c;

    public BookPersistenceHSQLDB(final String dbPath) {
        try {
            this.c = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException {
        String BookName = rs.getString("BookName");
        String authorName = rs.getString("authorName");
        int bookPicture = rs.getInt("bookPicture");
        String bookDescription = rs.getString("bookDescription");
        String bookCategory = rs.getString("bookCategory");
        double price = rs.getDouble("price");
        int ownerID = rs.getInt("ownerID");

        return new Book(BookName, authorName, bookPicture, bookDescription, bookCategory, price, ownerID);
    }

    @Override
    public List<Book> getBookSequential() {
        final List<Book> books = new ArrayList<>();

        try
        {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next())
            {
                final Book book = fromResultSet(rs);
                books.add(book);
            }
            rs.close();
            st.close();

            return books;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Book insertBook(Book currentBook) {
        try {
            final PreparedStatement st = c.prepareStatement("INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, currentBook.getName());
            st.setString(2, currentBook.getAuthor());
            st.setInt(3, currentBook.getPicture());
            st.setString(4, currentBook.getDescription());
            st.setString(5, currentBook.getCategory());
            st.setDouble(6, currentBook.getPrice());
            st.setInt(7, currentBook.getOwner());

            st.executeUpdate();

            return currentBook;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Book updateBook(Book currentBook, String book_Name, String author_Name, int book_Picture, String book_Description, String book_Category, double price ) {
        try {
            final PreparedStatement st = c.prepareStatement("UPDATE books SET authorName = ?, bookPicture = ?, bookDescription = ?, bookCategory = ?, price = ? WHERE BookName = ?");

            st.setString(1, author_Name);
            st.setInt(2, book_Picture);
            st.setString(3, book_Description);
            st.setString(4, book_Category);
            st.setDouble(5, price);
            st.setString(6, currentBook.getName());

            st.executeUpdate();

            return currentBook;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    
}
