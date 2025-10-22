package dao;

import java.sql.*;
import model.*;

public class AccountDAOjdbc implements AccountDAO {
    private Connection con;

    public AccountDAOjdbc(Connection con) {
        this.con = con;
    }

    public void loadAccount(Account account){
        try{
            con.setAutoCommit(false);

            String insertSql = "INSERT INTO accounts (email, password) VALUES (?, ?)";
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
        } catch (SQLException e) {
             try { 
                con.rollback(); 
                con.setAutoCommit(true);
            } catch (SQLException ignore) {}
            System.out.println("Error al cargar la cuenta en la BD: " + e.getMessage());
        }
    }


}
