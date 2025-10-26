package dao;

import java.sql.SQLException;
import java.util.List;
import model.*;

public interface ReviewDAO {
    void loadReview(Review rev) throws SQLException;  
    List<Review> findRejected() throws SQLException;  
    boolean approve(Review rev) throws SQLException;  
    List<Review> findByFilm(int filmId) throws SQLException;
    List<Review> findByAccount(int accountId) throws SQLException;
}
