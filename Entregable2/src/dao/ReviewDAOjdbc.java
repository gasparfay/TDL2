package dao;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class ReviewDAOjdbc implements ReviewDAO {

    private Connection con;

    public ReviewDAOjdbc(Connection con) {
        this.con = con;
    }

    @Override
    public void loadReview(Review rev) {
        try {
            con.setAutoCommit(false);

            String insertSql = "INSERT INTO REVIEW (RATING, TEXT, STATUS, CREATION_DATE, ACCOUNT_ID, FILM_ID) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setInt(1, rev.getRating().ordinal());
                ps.setString(2, rev.getText());
                ps.setInt(3, rev.getStatus().ordinal());
                ps.setTimestamp(4, new Timestamp(rev.getCreationDate().getTime()));
                ps.setInt(5, rev.getAccount().getAccId());
                ps.setInt(6, rev.getFilm().getFilmId());
                ps.executeUpdate();
            }

            String idSql = "SELECT last_insert_rowid()";
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(idSql)) {
                if (rs.next()) {
                    rev.setRevId(rs.getInt(1));
                }
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ignore) {}
            System.out.println("Error al cargar la reseña en la BD: " + e.getMessage());
        }
    }

    @Override
    public List<Review> findPending() {
        List<Review> pending = new ArrayList<>();
        String sql = "SELECT R.ID, R.RATING, R.TEXT, R.STATUS, R.CREATION_DATE, R.ACCOUNT_ID, R.FILM_ID, "
                   + "A.EMAIL, A.PASSWORD, F.TITLE, F.DIRECTOR, F.DURATION, F.GENRE "
                   + "FROM REVIEW R "
                   + "JOIN ACCOUNT A ON R.ACCOUNT_ID = A.ID "
                   + "JOIN FILM F ON R.FILM_ID = F.ID "
                   + "WHERE R.STATUS = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ReviewStatus.PENDING.ordinal());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review(
                        Rating.values()[rs.getInt("RATING")],
                        rs.getString("TEXT"),
                        new java.util.Date(rs.getTimestamp("CREATION_DATE").getTime())
                    );
                    review.setRevId(rs.getInt("ID"));
                    review.setStatus(ReviewStatus.values()[rs.getInt("STATUS")]);

                    Account account = new Account(rs.getString("EMAIL"), rs.getString("PASSWORD"));
                    account.setAccId(rs.getInt("ACCOUNT_ID"));
                    review.setAccount(account);

                    Film film = new Film(
                        rs.getString("TITLE"),
                        rs.getString("DIRECTOR"),
                        Duration.ofMinutes((long) rs.getDouble("DURATION")),
                        Genre.values()[rs.getInt("GENRE")]
                    );
                    film.setFilmId(rs.getInt("FILM_ID"));
                    review.setFilm(film);

                    pending.add(review);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las reseñas pendientes: " + e.getMessage());
        }

        return pending;
    }

    @Override
    public boolean modifyStatus(Review rev) {
        String sql = "UPDATE REVIEW SET STATUS = ? WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, rev.getStatus().ordinal());
            ps.setInt(2, rev.getRevId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar el estado de la reseña: " + e.getMessage());
            return false;
        }
    }
}
