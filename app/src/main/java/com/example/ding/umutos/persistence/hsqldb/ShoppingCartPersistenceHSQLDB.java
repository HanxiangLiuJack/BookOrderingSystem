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
import com.example.ding.umutos.persistence.ShoppingCartPersistence;

public class ShoppingCartPersistenceHSQLDB implements ShoppingCartPersistence {

    private final String dbPath;

    public ShoppingCartPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Wish fromResultSet(final ResultSet rs) throws SQLException {
        int bookID = rs.getInt("bookID");
        String userName = rs.getString("userName")
        String bookName = rs.getString("bookName");

        Double price = rs.getDouble("price");
        String ownerName = rs.getString("ownerName");

        Wish wish = new Book(userName, bookID, bookName, price, ownerName);

        return wish;
    }


    @Override
    public void insertShoppingCart(Wish wish, String userName){
        shoppingCartSequential();
        try (final Connection c = connection()) {
            PreparedStatement st = c.prepareStatement("INSERT INTO shoppingCart VALUES(?, ?, ?, ?, ?)");
            st.setString(1,userName );
            st.setInt(2,wish.getBookID());
            st.setString(3, wish.getName());
            st.setDouble(4, wish.getPrice());
            st.setString(5, wish.getOwner());

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Wish searchShoppingCart(int id){
        Wish wish = null;
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM shoppingCart WHERE bookID = ?");
            st.setInt(1, id);

            final ResultSet rs = st.executeQuery();

            if(rs.next()) {
                wish = fromResultSet(rs);
            }

            rs.close();
            st.close();

            return wish;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void deleteBookfromShoppingCart(int bookID ,String userName) {
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
    public List<Wish> getShoppingCartSequential(String userName) {
        final List<Wish> shoppingCart = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM shoppingCart WHERE userName = ?");
            st.setString(1, userName);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Wish wish = fromResultSet(rs);
                shoppingCart.add(wish);
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
            final PreparedStatement st = c.prepareStatement("DELETE FROM shoppingCart WHERE userName = ?");
            st.setString(1, userName);
            st.executeUpdate();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public List<Wish> shoppingCartSequential() {
        final List<Wish> shoppingCart = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM shoppingCart");
            while (rs.next()) {
                final Wish wish = fromResultSet(rs);
                shoppingCart.add(wish);
            }
            rs.close();
            st.close();

            return shoppingCart;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }




}
