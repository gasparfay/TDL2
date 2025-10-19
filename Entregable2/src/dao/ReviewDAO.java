package dao;

public interface ReviewDAO {
    public void submitReview(int userId, int filmId, String reviewText) throws Exception;
    public String getReviewStatus(int reviewId) throws Exception;
    public void updateReviewStatus(int reviewId, String status) throws Exception;
}
