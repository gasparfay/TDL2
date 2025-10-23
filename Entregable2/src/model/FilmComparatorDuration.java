package model;

import java.util.Comparator;

public class FilmComparatorDuration implements Comparator<Film> {

    @Override
    public int compare(Film f1, Film f2) {
        return Long.compare(f1.getDuration().toMinutes(),f2.getDuration().toMinutes());

    }

}
