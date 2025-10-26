package dao;

import java.util.List;
import model.Film;

public interface FilmDAO {
    boolean loadFilm(Film film);
    List<Film> findAll();
    public Film findById(int id);
}
