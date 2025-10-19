package dao;

import java.sql.SQLException;
import java.util.List;
import model.Film;


public interface FilmDAO {
    void loadFilm(Film film) throws SQLException;
    List<Film> findAll() throws SQLException;
}
