package dao;

import model.*;

public interface AccountDAO {
    void loadAccount(Account account);
    //List<Account> findAll() throws SQLException;
    //Account findByEmail(String email) throws SQLException;
    //boolean existsEmail(String email) throws SQLException;  
}
