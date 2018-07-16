package com.example.ding.umutos.persistence.hsqldb;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.ding.umutos.persistence.OrderPersistence;
import com.example.ding.umutos.objects.*;


public class OrderPersistenceHSQLDB  implements OrderPersistence{

    private final String dbPath;

    public OrderPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true","SA","");
    }

    private Order fromResultSet(final ResultSet rs) throws SQLException {
        final String bookName = rs.getString("bookName");
        final String buyerName = rs.getString("buyerName");
        final String sellerName = rs.getString("sellerName");
        final double price = rs.getDouble("price");

        final String buyerFirstName = rs.getString("buyerFirstName");
        final String buyerLastName = rs.getString("buyerLastName");
        final String postCode = rs.getString("postCode");
        final String phoneNumber = rs.getString("phoneNumber");
        final String address = rs.getString("address");

        OrderInfo orderInfo = new OrderInfo(buyerFirstName,buyerLastName,postCode,phoneNumber,address);
        Order newOrder =  new Order(bookName,buyerName,sellerName,price,orderInfo);

        return newOrder;
    }


    @Override
    public Order insertOrder(Order currentOrder) {
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?)");
            st.setString(1,currentOrder.getBookName());
            st.setString(2,currentOrder.getBuyerName());
            st.setString(3,currentOrder.getSellerName());
            st.setDouble(4,currentOrder.getPrice());
            st.setString(5,currentOrder.getBuyerFirstName());
            st.setString(6,currentOrder.getBuyerLastName());
            st.setString(7,currentOrder.getPostCode());
            st.setString(8,currentOrder.getPhoneNumber());
            st.setString(9,currentOrder.getAddress());

            st.executeUpdate();

            return currentOrder;
        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Order> getBuyerOrders(String userName) {
        final List<Order> orders = new ArrayList<>();
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM orders WHERE buyerName = ?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            while(rs.next()){
                final Order order = fromResultSet(rs);
                orders.add(order);
            }
            rs.close();
            st.close();

            return orders;

        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Order> getSellerOrders(String sellerName) {
        final List<Order> orders = new ArrayList<>();
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM orders WHERE sellerName = ?");
            st.setString(1, sellerName);
            final ResultSet rs = st.executeQuery();
            while(rs.next()){
                final Order order = fromResultSet(rs);
                orders.add(order);
            }
            rs.close();
            st.close();

            return orders;

        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

}
