package com.example.ding.umutos.persistence.hsqldb;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.ding.umutos.persistence.AccountPersistence;
import com.example.ding.umutos.objects.Account;

public class AccountPersistenceHSQLDB implements AccountPersistence{

    private final String dbPath;

    private int maxUserID;

    public AccountPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
        maxUserID = 0;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath+ ";shutdown=true" ,"SA","");
    }

    private Account fromResultSet(final ResultSet rs) throws SQLException {
        final int userID = rs.getInt("accountID");
        final String userName = rs.getString("userName");
        final String password = rs.getString("password");

        if(userID>maxUserID){
            maxUserID = userID;
        }

        Account account =  new Account(userName,password);
        account.setUserID(userID);
        return account;
    }

    @Override
    public List<Account> getAccountSequential() {
        final List<Account> accounts = new ArrayList<>();
        try(final Connection c = connection()){
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM accounts");
            while (rs.next()){
                final Account account = fromResultSet(rs);
                accounts.add(account);
            }
            rs.close();
            st.close();

            return accounts;
        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public Account getAccountByID(int userID) {
        Account account = null;
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM accounts WHERE accountID = ?");
            st.setInt(1, userID);
            final ResultSet rs = st.executeQuery();
            if(rs.next()){
                account = fromResultSet(rs);
            }
            return account;

        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }


    @Override
    public Account insertAccount(Account currentAccount) {
        getAccountSequential();
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("INSERT INTO accounts VALUES(?,?,?)");
            st.setInt(1, maxUserID+1);
            st.setString(2,currentAccount.getUserName());
            st.setString(3,currentAccount.getPassword());

            st.executeUpdate();

            currentAccount.setUserID(maxUserID+1);

            return currentAccount;
        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public Account updateAccount(Account currentAccount) {
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("UPDATE accounts SET userName = ?, passWord = ? WHERE userID = ?");
            st.setString(1, currentAccount.getUserName());
            st.setString(2, currentAccount.getPassword());
            st.setInt(3,currentAccount.getUserID());

            st.executeUpdate();

            return currentAccount;
        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteAccount(Account currentAccount) {
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("DELETE FROM accounts WHERE userID = ?");
            st.setInt(1, currentAccount.getUserID());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


}
