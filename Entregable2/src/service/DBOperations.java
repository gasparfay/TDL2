package service;

import dao.*;
import model.*;

public class DBOperations {
    private AccountDAO accountDAO;

    public DBOperations() {
        this.accountDAO = new AccountDAOjdbc(MyConnection.getConnection());
    }

    public void loadAccount(Account account){
        accountDAO.loadAccount(account);
    }
}
