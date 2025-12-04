package model;
import java.time.Duration;
import javax.swing.ImageIcon;


public class Film {
    private int filmId;
    private String title;
    private String director;
    private String synopsis;
    private Duration duration;
    private Genre genre;
    private int releaseYear;     
    private String posterUrl;    
    private float averageRating;
    private ImageIcon posterImage;

    public Film(String title, String director, Duration duration, Genre genre, int releaseYear, String posterUrl, float averageRating) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.averageRating = averageRating;
    }

    public Film(String title, String director, Duration duration, Genre genre) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
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

    public int getReleaseYear() { 
        return releaseYear;
    }

    public String getPosterUrl() { 
        return posterUrl; 
    }

    public float getAverageRating() { 
        return averageRating; 
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

    public void setReleaseYear(int releaseYear) { 
        this.releaseYear = releaseYear; 
    }

    public void setPosterUrl(String posterUrl) { 
        this.posterUrl = posterUrl; 
    }

    public void setAverageRating(float averageRating) { 
        this.averageRating = averageRating; 
    }

    public ImageIcon getPosterImage() {
        return posterImage;
    }
    
    public void setPosterImage(ImageIcon posterImage) {
        this.posterImage = posterImage;
    }

}
