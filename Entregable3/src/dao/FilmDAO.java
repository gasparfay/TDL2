package dao;

import java.util.List;
import model.Film;

public interface FilmDAO {
    boolean loadFilm(Film film);
    List<Film> findAll();
    Film findById(int id);
    boolean isTableEmpty();
    void loadFilmsInBatch(List<Film> films);
}
