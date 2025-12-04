package dao;

import java.util.List;
import model.*;

public interface ReviewDAO {
    boolean loadReview(Review rev);  
    List<Review> findPending();  
    boolean modifyStatus(Review rev);  
    List<Integer> findFilmIdsByProfile(Profile profile);
}
