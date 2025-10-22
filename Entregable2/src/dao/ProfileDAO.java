package dao;

import java.sql.SQLException;
import java.util.List;
import model.*;

public interface ProfileDAO {
    void loadProfile(Profile profile) throws SQLException;
    List<Profile> findByAccount(Account acc) throws SQLException;
}
