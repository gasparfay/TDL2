package model;

import java.util.Date;

public class Review {

    private Rating rating;
    private String text;
    private Date creationDate;
    private ReviewStatus status;
    private Date lastModified;

 
    public Review(Rating rating, String text, Date creationDate) {
        this.rating = rating;
        this.text = text;
        this.creationDate = creationDate;
        // Se puede inicializar el estado por defecto, por ejemplo, como PENDIENTE.
        this.status = ReviewStatus.PENDING;
        // La fecha de modificación inicial puede ser la misma que la de creación.
        this.lastModified = creationDate;
    }

    // --- Getters y Setters ---

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
