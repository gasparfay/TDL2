package dao;

import model.ReviewStatus;

public interface ReviewDAO {
    public void submitReview(int userId, int filmId, String reviewText) throws Exception;
    public ReviewStatus getReviewStatus(int reviewId) throws Exception;
    public void updateReviewStatus(int reviewId, String status) throws Exception;
}
