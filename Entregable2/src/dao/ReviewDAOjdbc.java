package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

public class ReviewDAOjdbc implements ReviewDAO<Review> {
    
    private Connection connection;

    public ReviewDAOjdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Review review) throws DAOException {
        try {
            String sql = "INSERT INTO reviews (rating, comment, movie_id, user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, review.getRating());
            statement.setString(2, review.getComment());
            statement.setInt(3, review.getMovieId());
            statement.setInt(4, review.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al insertar review", e);
        }
    }

    @Override
    public void update(Review review) throws DAOException {
        try {
            String sql = "UPDATE reviews SET rating = ?, comment = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, review.getRating());
            statement.setString(2, review.getComment());
            statement.setInt(3, review.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar review", e);
        }
    }

    @Override
    public void delete(Review review) throws DAOException {
        try {
            String sql = "DELETE FROM reviews WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, review.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar review", e);
        }
    }

    @Override
    public List<Review> getAll() throws DAOException {
        List<Review> reviews = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reviews";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(createReview(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener reviews", e);
        }
        return reviews;
    }

    @Override
    public Review getById(int id) throws DAOException {
        try {
            String sql = "SELECT * FROM reviews WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createReview(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener review por id", e);
        }
        return null;
    }

    private Review createReview(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setRating(resultSet.getInt("rating"));
        review.setComment(resultSet.getString("comment"));
        review.setMovieId(resultSet.getInt("movie_id"));
        review.setUserId(resultSet.getInt("user_id"));
        return review;
    }
}
