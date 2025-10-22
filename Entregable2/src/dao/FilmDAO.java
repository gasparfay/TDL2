package dao;

import java.sql.SQLException;
import java.util.List;
import model.*;

public interface FilmDAO {
    void loadFilm(Film film) throws SQLException;
    List<Film> findAll() throws SQLException;
    Film findById(int id) throws SQLException;
}
