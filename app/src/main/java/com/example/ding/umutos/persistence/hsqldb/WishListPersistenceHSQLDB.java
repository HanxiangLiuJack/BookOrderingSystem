package com.example.ding.umutos.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.ding.umutos.objects.Wish;
import com.example.ding.umutos.persistence.WishListPersistence;


public class WishListPersistenceHSQLDB implements WishListPersistence {

    private final String dbPath;

    public WishListPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }



    private Wish fromResultSet(final ResultSet rs) throws SQLException {
        int bookID = rs.getInt("bookID");
        String bookName = rs.getString("bookName");
        String userName = rs.getString("userName");
        String ownerName = rs.getString("ownerName");

        Wish wish = new Wish(userName, ownerName, bookName);
        wish.setBookID(bookID);

        return wish;
    }

    @Override
    public List<Wish> getWishListSequential() {
        final List<Wish> wishList = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM wishList");
            while (rs.next()) {
                final Wish wish = fromResultSet(rs);
                wishList.add(wish);
            }
            rs.close();
            st.close();

            return wishList;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Wish insertWishList(Wish wish) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO wishList (ownerName,bookName,userName) VALUES(?,?,?)");
            st.setString(1,wish.getAuthorName() );
            st.setString(2, wish.getName());
            st.setString(3, wish.getUserName());

            st.executeUpdate();

            return  wish;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Wish searchWishList(int id) {
        Wish wish = null;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM wishList WHERE bookID = ?");
            st.setInt(1, id);

            final ResultSet rs = st.executeQuery();

            if (rs.next()) {
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
    public List<Wish> getUserWishListSequential(String userName) {
        final List<Wish> wishList = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM wishList WHERE userName= ?");
            st.setString(1, userName);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Wish wish = fromResultSet(rs);
                wishList.add(wish);
            }

            rs.close();
            st.close();

            return wishList;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteWishList(int id ,String userName) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM wishList WHERE bookID = ? AND userName = ?");
            st.setInt(1, id);
            st.setString(2,userName);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}