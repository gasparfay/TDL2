package dao;

import java.util.List;
import model.Account;

public interface AccountDAO {
    boolean loadAccount(Account account);
    boolean emailExists(String email);
    List<Account> findAll();
}
