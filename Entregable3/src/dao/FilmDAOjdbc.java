package dao;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class FilmDAOjdbc implements FilmDAO {
    private Connection con;

    public FilmDAOjdbc(Connection con) {
        this.con = con;
    }

    @Override
    public boolean loadFilm(Film film) {
        try {
            con.setAutoCommit(false);

            String sql = "INSERT INTO FILM (TITLE, DIRECTOR, SYNOPSIS, DURATION, GENRE, AVERAGE_RATING, RELEASE_YEAR, POSTER) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, film.getTitle());
                ps.setString(2, film.getDirector());
                ps.setString(3, film.getSynopsis());
                ps.setInt(4, (int) film.getDuration().toMinutes());
                ps.setInt(5, film.getGenre().ordinal());
                ps.setFloat(6, film.getAverageRating());
                ps.setInt(7, film.getReleaseYear());
                ps.setString(8, film.getPosterUrl());
                ps.executeUpdate();
            }

            // Recuperar ID
            try (Statement st = con.createStatement(); 
                 ResultSet rs = st.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    film.setFilmId(rs.getInt(1));
                }
            }

            con.commit();
            con.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ignored) {}
            System.out.println("Error al insertar película: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void loadFilmsInBatch(List<Film> films) {
        try {
            con.setAutoCommit(false);

            String sql = "INSERT INTO FILM (TITLE, DIRECTOR, SYNOPSIS, DURATION, GENRE, AVERAGE_RATING, RELEASE_YEAR, POSTER) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (Film film : films) {
                    ps.setString(1, film.getTitle());
                    ps.setString(2, film.getDirector());
                    ps.setString(3, film.getSynopsis());
                    ps.setInt(4, (int) film.getDuration().toMinutes());
                    ps.setInt(5, film.getGenre().ordinal());
                    ps.setFloat(6, film.getAverageRating());
                    ps.setInt(7, film.getReleaseYear());
                    ps.setString(8, film.getPosterUrl());
                    ps.addBatch();  // Agregar al batch sin ejecutar
                }
                ps.executeBatch();  // Ejecutar TODO de una vez
            }

            con.commit();
            con.setAutoCommit(true);
            System.out.println("Cargadas " + films.size() + " películas en batch");

        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ignored) {}
            System.err.println("Error en batch insert: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();

        String sql = "SELECT * FROM FILM";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Film film = new Film(
                    rs.getString("TITLE"),
                    rs.getString("DIRECTOR"),
                    Duration.ofMinutes(rs.getInt("DURATION")),
                    Genre.values()[rs.getInt("GENRE")],
                    rs.getInt("RELEASE_YEAR"),
                    rs.getString("POSTER"),
                    rs.getFloat("AVERAGE_RATING")
                );

                film.setFilmId(rs.getInt("ID"));
                film.setSynopsis(rs.getString("SYNOPSIS"));

                films.add(film);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer FILM: " + e.getMessage());
        }

        return films;
    }

    @Override
    public Film findById(int id) {
        String sql = "SELECT * FROM FILM WHERE ID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Film film = new Film(
                        rs.getString("TITLE"),
                        rs.getString("DIRECTOR"),
                        Duration.ofMinutes(rs.getInt("DURATION")),
                        Genre.values()[rs.getInt("GENRE")],
                        rs.getInt("RELEASE_YEAR"),
                        rs.getString("POSTER"),
                        rs.getFloat("AVERAGE_RATING")
                    );

                    film.setFilmId(rs.getInt("ID"));
                    film.setSynopsis(rs.getString("SYNOPSIS"));

                    return film;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en findById FILM: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean isTableEmpty() {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM FILM")) {

            if (rs.next()) return rs.getInt(1) == 0;

        } catch (SQLException e) {
            System.out.println("Error al chequear si FILM está vacía: " + e.getMessage());
        }
        return true;
    }
}
