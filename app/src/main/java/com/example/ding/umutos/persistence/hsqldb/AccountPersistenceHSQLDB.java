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

    public AccountPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath+ ";shutdown=true" ,"SA","");
    }

    private Account fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("userName");
        final String password = rs.getString("password");
        final double rate = rs.getDouble("rate");
        final int ratedPerson = rs.getInt("ratedPerson");
        Account account =  new Account(userName,password);
        account.setRate(rate);
        account.setRatedPerson(ratedPerson);
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
    public Account getAccountByUserName(String userName) {
        Account account = null;
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM accounts WHERE userName = ?");
            st.setString(1, userName);
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
            final PreparedStatement st = c.prepareStatement("INSERT INTO accounts VALUES(?,?,?,?)");
            st.setString(1,currentAccount.getUserName());
            st.setString(2,currentAccount.getPassword());
            st.setDouble(3,currentAccount.getRate());
            st.setInt(4,currentAccount.getRatedPerson());
            st.executeUpdate();

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
            st.setString(3,currentAccount.getUserName());

            st.executeUpdate();

            return currentAccount;
        } catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteAccount(Account currentAccount) {
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("DELETE FROM accounts WHERE userName = ?");
            st.setString(1, currentAccount.getUserName());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void updateRating(String userName, double rate, int ratedPerson)
    {
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("UPDATE accounts SET rate = ?, ratedPerson = ? WHERE userName = ?");
            st.setDouble(1, rate);
            st.setInt(2,ratedPerson);
            st.setString(3,userName);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
