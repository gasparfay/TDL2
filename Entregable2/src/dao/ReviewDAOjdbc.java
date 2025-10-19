package dao;
import java.sql.Connection;

import service.MyConnection;
import model.Review;


public class ReviewDAOjdbc implements ReviewDAO {
    private Connection con;
	
	public ReviewDAOjdbc() {
		this.con = MyConnection.getConnection();
	}

    public void submitReview (Review review) {
		try {
			String query = "INSERT INTO review(rating,text,creationDate,reviewStatus,lastModified) VALUES(?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.clearParameters();
			st.setInt(1, review.getRating());
			st.setString(2, review.getText());
            st.setDate(3, new java.sql.Date(review.getCreationDate().getTime()));
            st.setInt(4, review.getStatus());
            st.setDate(5, new java.sql.Date(review.getLastModified().getTime()));
			st.executeUpdate();
            System.out.println("Review guardada correctamente.");
			st.close();
		}catch(SQLException e) {
			System.out.print("Error de SQL: "+e.getMessage());
		}
	}
}
