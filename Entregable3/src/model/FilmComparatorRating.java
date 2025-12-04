package model;

import java.util.Comparator;

public class FilmComparatorRating implements Comparator<Film> {

    @Override
    public int compare(Film f1, Film f2) {
        return Float.compare(f2.getAverageRating(), f1.getAverageRating());
    }
}
