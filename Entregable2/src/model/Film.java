package model;
import java.time.Duration;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;


public class Film {
  String title;

  List<String> cast;

  String director;

  String synopsis;

  Duration duration;

  EnumSet<Language> audioLangs;

  EnumSet<Language> subtitleTracks;

  UUID contentId;

  EnumSet<Genre> genres;

  EnumSet<Country> availableCountries;

  boolean isPublished;

    public Film(String title, List<String> cast, String director, String synopsis, Duration duration,
        EnumSet<Language> audioLangs, EnumSet<Language> subtitleTracks,
        EnumSet<Genre> genres, EnumSet<Country> availableCountries, boolean isPublished) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.synopsis = synopsis;
        this.duration = duration;
        this.audioLangs = audioLangs;
        this.subtitleTracks = subtitleTracks;
        this.genres = genres;
        this.availableCountries = availableCountries;
        this.isPublished = isPublished;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCast() {
        return cast;
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

    public EnumSet<Language> getAudioLangs() {
        return audioLangs;
    }

    public EnumSet<Language> getSubtitleTracks() {
        return subtitleTracks;
    }

    public UUID getContentId() {
        return contentId;
    }

    public EnumSet<Genre> getGenres() {
        return genres;
    }

    public EnumSet<Country> getAvailableCountries() {
        return availableCountries;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
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

    public void setAudioLangs(EnumSet<Language> audioLangs) {
        this.audioLangs = audioLangs;
    }

    public void setSubtitleTracks(EnumSet<Language> subtitleTracks) {
        this.subtitleTracks = subtitleTracks;
    }

    public void setContentId(UUID contentId) {
        this.contentId = contentId;
    }

    public void setGenres(EnumSet<Genre> genres) {
        this.genres = genres;
    }

    public void setAvailableCountries(EnumSet<Country> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public void setPublished(boolean published) {
        this.isPublished = published;
    }

    
}
