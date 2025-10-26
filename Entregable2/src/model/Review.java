package model;


import java.util.Date;

public class Review {

    private int revId;
    private Rating rating;
    private String text;
    private Date creationDate;
    private ReviewStatus status;
    private Film film;
    private Account account;

 
    public Review(Rating rating, String text, Date creationDate) {
        this.rating = rating;
        this.text = text;
        this.creationDate = creationDate;
        this.status = ReviewStatus.PENDING;
    }

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

}
