package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class AccountDAOjdbc implements AccountDAO {
    private Connection con;

    public AccountDAOjdbc(Connection con) {
        this.con = con;
    }

    @Override
    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM ACCOUNT WHERE email = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el email en la BD: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean loadAccount(Account account){
        try{
            con.setAutoCommit(false);

            String insertSql = "INSERT INTO ACCOUNT (email, password) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassword());
                ps.executeUpdate();
            }

            String idSql = "SELECT last_insert_rowid()";
            try (Statement st = con.createStatement();ResultSet rs = st.executeQuery(idSql)){
                if (rs.next()) {
                    account.setAccId(rs.getInt(1));
                }
            }

            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
             try { 
                con.rollback(); 
                con.setAutoCommit(true);
                return false;
            } catch (SQLException ignore) {}
            System.out.println("Error al cargar la cuenta en la BD: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT ID, EMAIL, PASSWORD FROM ACCOUNT";
        
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Account account = new Account(rs.getString("EMAIL"), rs.getString("PASSWORD"));
                account.setAccId(rs.getInt("ID"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las cuentas de la BD: " + e.getMessage());
            accounts=null;
        }
        return accounts;
    }

    @Override
    public Account findById(int accId) {
        String sql = "SELECT ID, EMAIL, PASSWORD FROM ACCOUNT WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(rs.getString("EMAIL"), rs.getString("PASSWORD"));
                    account.setAccId(rs.getInt("ID"));
                    return account;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la cuenta por ID en la BD: " + e.getMessage());
        }
        return null;
    }

    public Account findByEmail(String email) {
        String sql = "SELECT ID, EMAIL, PASSWORD FROM ACCOUNT WHERE EMAIL = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(rs.getString("EMAIL"), rs.getString("PASSWORD"));
                    account.setAccId(rs.getInt("ID"));
                    return account;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la cuenta por email en la BD: " + e.getMessage());
        }
        return null;
    }
}
