package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ProfileDAOjdbc implements ProfileDAO {
    private Connection con;

    public ProfileDAOjdbc(Connection con) {
        this.con = con;
    }

    @Override
    public boolean loadProfile(Profile profile) {
        try {
            con.setAutoCommit(false);

            String insertSql;
            if (profile.getAccount()!=null) {
                insertSql = "INSERT INTO PROFILE (name, account_id) VALUES (?, ?)";
                try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                    ps.setString(1, profile.getName());
                    ps.setInt(2, profile.getAccount().getAccId());
                    ps.executeUpdate();
                }
            } else {
                insertSql = "INSERT INTO PROFILE (name, account_id) VALUES (?, NULL)";
                try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                    ps.setString(1, profile.getName());
                    ps.executeUpdate();
                }
            }

            String idSql = "SELECT last_insert_rowid()";
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(idSql)) {
                if (rs.next()) {
                    profile.setProfileId(rs.getInt(1));
                }
            }

            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ignore) {}
            System.out.println("Error al cargar el perfil en la BD: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT p.ID, p.NAME, a.ID AS ACCOUNT_ID, a.EMAIL, a.PASSWORD "
                   + "FROM PROFILE p "
                   + "LEFT JOIN ACCOUNT a ON p.ACCOUNT_ID = a.ID";
        
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Profile profile = new Profile(rs.getString("NAME"));
                profile.setProfileId(rs.getInt("ID"));
                int accId = rs.getInt("ACCOUNT_ID");
                if (!rs.wasNull()) {
                    Account account = new Account(rs.getString("EMAIL"), rs.getString("PASSWORD"));
                    account.setAccId(accId);
                    profile.setAccount(account);
                }
                profiles.add(profile);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los perfiles: " + e.getMessage());
        }
        return profiles;
    }

    @Override
    public boolean associateWithAccount(Profile profile, Account account) {
        try {
            String updateSql = "UPDATE PROFILE SET ACCOUNT_ID = ? WHERE ID = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                if (account != null && account.getAccId() != 0) {
                    ps.setInt(1, account.getAccId());
                } else {
                    ps.setNull(1, Types.INTEGER);
                }
                ps.setInt(2, profile.getProfileId());
                int rowsAffected = ps.executeUpdate();
            
                if (rowsAffected > 0) {
                    profile.setAccount(account);
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al asociar el perfil con la cuenta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Profile> findByAccount(Account account){
        List<Profile> profiles = new ArrayList<>();
        if (account == null || account.getAccId() == 0) {
            return profiles; 
        }
        String sql = "SELECT ID, NAME FROM PROFILE WHERE ACCOUNT_ID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, account.getAccId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Profile profile = new Profile(rs.getString("NAME"));
                    profile.setProfileId(rs.getInt("ID"));
                    profile.setAccount(account);
                    profiles.add(profile);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener perfiles de la cuenta: " + e.getMessage());
        }
        return profiles;
    }



}