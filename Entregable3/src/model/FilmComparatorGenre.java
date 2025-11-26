package model;

import java.util.Comparator;

public class FilmComparatorGenre implements Comparator<Film> {

    @Override
    public int compare(Film f1, Film f2) {
        return f1.getGenre().name().compareTo(f2.getGenre().name());
    }

}
