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
            
            String insertSql = "INSERT INTO FILM (TITLE, DIRECTOR, SYNOPSIS, DURATION, GENRE, RATING_PROMEDIO, ANIO, POSTER) " +  // NUEVO
                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";                                                                  // NUEVO
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setString(1, film.getTitle());
                ps.setString(2, film.getDirector());
                ps.setString(3, film.getSynopsis());                        // NUEVO
                ps.setDouble(4, film.getDuration().toMinutes());
                ps.setInt(5, film.getGenre().ordinal());
                ps.setFloat(6, film.getAverageRating());                    // NUEVO
                ps.setInt(7, film.getReleaseYear());                        // NUEVO
                ps.setString(8, film.getPosterUrl());                       // NUEVO
                ps.executeUpdate();
            }
            
            String idSql = "SELECT last_insert_rowid()";
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(idSql)) {
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
            } catch (SQLException ignore) {}
            System.out.println("Error al cargar la película en la BD: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM FILM";
        
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                double minutes = rs.getDouble("DURATION");
                int genreOrd = rs.getInt("GENRE");

                // SE USA EL CONSTRUCTOR COMPLETO
                Film film = new Film(
                    rs.getString("TITLE"),
                    rs.getString("DIRECTOR"),
                    Duration.ofMinutes((long) minutes),
                    Genre.values()[genreOrd],
                    rs.getInt("ANIO"),               // NUEVO
                    rs.getString("POSTER"),          // NUEVO
                    rs.getFloat("RATING_PROMEDIO")   // NUEVO
                );

                film.setFilmId(rs.getInt("ID"));
                film.setSynopsis(rs.getString("SYNOPSIS")); // NUEVO

                films.add(film);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las películas: " + e.getMessage());
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
                    double minutes = rs.getDouble("DURATION");
                    int genreOrd = rs.getInt("GENRE");

                    Film film = new Film(
                        rs.getString("TITLE"),
                        rs.getString("DIRECTOR"),
                        Duration.ofMinutes((long) minutes),
                        Genre.values()[genreOrd],
                        rs.getInt("ANIO"),               // NUEVO
                        rs.getString("POSTER"),          // NUEVO
                        rs.getFloat("RATING_PROMEDIO")   // NUEVO
                    );

                    film.setFilmId(rs.getInt("ID"));
                    film.setSynopsis(rs.getString("SYNOPSIS")); // NUEVO

                    return film;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la película: " + e.getMessage());
        }
        return null;
    }
}
