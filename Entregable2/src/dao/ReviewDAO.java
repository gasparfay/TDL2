package dao;

import java.util.List;
import model.*;

public interface ReviewDAO {
    void loadReview(Review rev);  
    List<Review> findRejected();  
    boolean approve(Review rev);  
    List<Review> findByFilm(int filmId);
    List<Review> findByAccount(int accountId);
}
