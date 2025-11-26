package service;

import dao.*;
import model.*;

public class Operations {
    private AccountDAO accountDAO;
    private ProfileDAO profileDAO;
    private FilmDAO filmDAO;
    private ReviewDAO reviewDAO;

    public Operations() {
        this.accountDAO = new AccountDAOjdbc(MyConnection.getConnection());
        this.profileDAO = new ProfileDAOjdbc(MyConnection.getConnection());
        this.filmDAO = new FilmDAOjdbc(MyConnection.getConnection());
        this.reviewDAO = new ReviewDAOjdbc(MyConnection.getConnection());
    }

    public boolean existsAccount(String email) {
        return accountDAO.emailExists(email);
    }

    public Account getAccount(String email) {
        return accountDAO.findByEmail(email);
    }

    public void addAccount(Account acc) {
        accountDAO.loadAccount(acc);
    }
   
}