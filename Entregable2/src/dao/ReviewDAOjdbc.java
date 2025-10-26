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


}
