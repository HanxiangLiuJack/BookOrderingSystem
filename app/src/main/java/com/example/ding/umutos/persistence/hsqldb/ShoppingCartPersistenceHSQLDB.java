package com.example.ding.umutos.persistence.hsqldb;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;

public class ShoppingCartPersistenceHSQLDB implements ShoppingCartPersistence {

    private final String dbPath;
    private int maxBookID;

    public ShoppingCartPersistenceHSQLDB(final String dbPath) {
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
        String ownerName = rs.getString("ownerName");

        if (bookID > maxBookID) {
            maxBookID = bookID;
        }

        Book book = new Book(bookName, authorName, bookPicture, bookDescription, bookCategory, price, ownerName);
        book.setBookID(bookID);
        return book;
    }


    @Override
    public void insertShoppingCart(Book currentBook, String userName){
        shoppingCartSequential();
        try (final Connection c = connection()) {
            PreparedStatement st = c.prepareStatement("INSERT INTO shoppingCart VALUES(?, ?)");
            st.setString(1,currentBook.getName() );
            st.setString(2, userName);

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Book searchShoppingCart(int id){
        Book book = null;
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM shoppingCart WHERE bookID = ?");
            st.setInt(1, id);

            final ResultSet rs = st.executeQuery();

            if(rs.next()) {
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
    public void deleteBookfromShoppingCart(int bookID, String userName) {
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("DELETE FROM shoppingCart WHERE bookID = ? AND userName = ?");
            st.setInt(1, bookID);
            st.setString(2,userName);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Book> getShoppingCartSequential(String userName) {
        final List<Book> shoppingCart = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM shoppingCart WHERE ownerName = ?");
            st.setString(1, userName);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Book book = fromResultSet(rs);
                shoppingCart.add(book);
            }

            rs.close();
            st.close();

            return shoppingCart;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void clearShoppingCart(String userName) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM shoppingCart WHERE ownerName = ?");
            st.setString(1, userName);

            st.executeUpdate();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public List<Book> shoppingCartSequential() {
        final List<Book> shoppingCart = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM shoppingCart");
            while (rs.next()) {
                final Book book = fromResultSet(rs);
                shoppingCart.add(book);
            }
            rs.close();
            st.close();

            return shoppingCart;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }




}
