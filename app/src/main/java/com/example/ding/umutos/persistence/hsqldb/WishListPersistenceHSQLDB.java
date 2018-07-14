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
import com.example.ding.umutos.persistence.WishListPersistence;


public class WishListPersistenceHSQLDB implements WishListPersistence {

    private final String dbPath;
    private int maxBookID;

    public WishListPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
        maxBookID = 0;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException {
        int bookID = rs.getInt("bookID");
        String bookName = rs.getString("bookName");
        String authorName = rs.getString("authorName");
        int bookPicture = rs.getInt("bookPicture");
        String bookDescription = rs.getString("bookDescription");
        String bookCategory = rs.getString("bookCategory");
        Double price = rs.getDouble("price");
        int ownerID = rs.getInt("ownerID");

        if (bookID > maxBookID) {
            maxBookID = bookID;
        }

        Book book = new Book(bookName, authorName, bookPicture, bookDescription, bookCategory, price, ownerID);
        book.setBookID(bookID);
        return book;
    }

    @Override
    public List<Book> getWishListSequential() {
        final List<Book> books = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                final Book book = fromResultSet(rs);
                books.add(book);
            }
            rs.close();
            st.close();

            return books;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void insertWishList(Book currentBook,int userID) {
        getWishListSequential();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO books VALUES(NULL,?,?)");
            st.setString(1,currentBook.getName() );
            st.setInt(2, userID);

            st.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Book searchWishList(int id) {
        Book book = null;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM books WHERE bookID = ?");
            st.setInt(1, id);

            final ResultSet rs = st.executeQuery();

            if (rs.next()) {
                book = fromResultSet(rs);
            }

            rs.close();
            st.close();

            return book;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Book> getUserWishListSequential(int userID) {
        final List<Book> books = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM books WHERE ownerID = ?");
            st.setInt(1, userID);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Book book = fromResultSet(rs);
                books.add(book);
            }

            rs.close();
            st.close();

            return books;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteWishList(int id) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM books WHERE bookID = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}