package dao;
import java.sql.*;
import model.ReviewStatus;
import service.MyConnection;


public class ReviewDAOjdbc implements ReviewDAO {
    private Connection con;
	
	public ReviewDAOjdbc() {
		this.con = MyConnection.getConnection();
	}

    public void submitReview(int userId, int filmId, String reviewText){
		return;
	}

	public ReviewStatus getReviewStatus(int reviewId){
		return ReviewStatus.PENDING;
	}

	public void updateReviewStatus(int reviewId, String status){
	}

}
