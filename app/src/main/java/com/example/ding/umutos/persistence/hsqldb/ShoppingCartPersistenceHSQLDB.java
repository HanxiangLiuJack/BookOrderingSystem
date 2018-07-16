package com.example.ding.umutos.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.ding.umutos.objects.Item;
import com.example.ding.umutos.persistence.ShoppingCartPersistence;

public class ShoppingCartPersistenceHSQLDB implements ShoppingCartPersistence {

    private final String dbPath;

    public ShoppingCartPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Item fromResultSet(final ResultSet rs) throws SQLException {
        int bookID = rs.getInt("bookID");
        String bookName = rs.getString("bookName");

        Double price = rs.getDouble("price");
        String userName = rs.getString("userName");

        Item item = new Item(userName,bookID, bookName, price);

        return item;
    }


    @Override
    public void insertShoppingCart(Item item){

        try (final Connection c = connection()) {
            PreparedStatement st = c.prepareStatement("INSERT INTO shoppingCart VALUES(?, ?, ?, ?)");
            st.setString(1,item.getUserName());
            st.setInt(2,item.getBookID());
            st.setString(3, item.getName());
            st.setDouble(4, item.getPrice());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Item searchShoppingCart(int id){
        Item item = null;
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM shoppingCart WHERE bookID = ?");
            st.setInt(1, id);

            final ResultSet rs = st.executeQuery();

            if(rs.next()) {
                item = fromResultSet(rs);
            }

            rs.close();
            st.close();

            return item;

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
    public List<Item> getShoppingCartSequential(String userName) {
        final List<Item> shoppingCart = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM shoppingCart WHERE userName = ?");
            st.setString(1, userName);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Item item = fromResultSet(rs);
                shoppingCart.add(item);
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
    public List<Item> shoppingCartSequential() {
        final List<Item> shoppingCart = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM shoppingCart");
            while (rs.next()) {
                final Item item = fromResultSet(rs);
                shoppingCart.add(item);
            }
            rs.close();
            st.close();

            return shoppingCart;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }




}
