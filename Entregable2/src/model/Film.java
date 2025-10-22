package model;
import java.time.Duration;


public class Film {
    private int filmId;
    private String title;
    private String director;
    private String synopsis;
    private Duration duration;
    private Genre genre;

    public Film(String title, String director, String synopsis, Duration duration, Genre genre) {
        this.title = title;
        this.director = director;
        this.synopsis = synopsis;
        this.duration = duration;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Duration getDuration() {
        return duration;
    }
    public Genre getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
