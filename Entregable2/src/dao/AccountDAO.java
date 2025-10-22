package dao;

import java.sql.SQLException;
import java.util.List;
import model.*;

public interface AccountDAO {
    void loadAccount(Account account) throws SQLException;
    List<Account> findAll() throws SQLException;
    Account findByEmail(String email) throws SQLException;
    boolean existsEmail(String email) throws SQLException;  
}
