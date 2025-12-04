package service;

import dao.*;
import java.util.*;
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

    public void addProfile(Profile prof) {
        profileDAO.loadProfile(prof);
    }

    public List<Profile> getProfiles(Account acc){
        return profileDAO.findByAccount(acc);
    }

    public boolean isFilmTableEmpty() {
        return filmDAO.isTableEmpty();
    }

    public void loadFilm(Film film) {
        filmDAO.loadFilm(film);
    }

    public void loadFilmsInBatch(List<Film> films) {
        filmDAO.loadFilmsInBatch(films);
    }

    public List<Film> getAllFilms() {
        return filmDAO.findAll();
    }

    // Devuelve las 10 películas con mejor rating de la lista recibida
    public List<Film> getTop10Films(List<Film> films) {
        if (films == null || films.isEmpty()) {
            return new ArrayList<>();
        }
        films.sort(new FilmComparatorRating());

        // Devolver una copia de los primeros 10 elementos
        int end = Math.min(10, films.size());
        return new ArrayList<>(films.subList(0, end));
    }

    // Devuelve 10 películas aleatorias de la lista recibida
    public List<Film> get10RandomFilms(List<Film> films) {
        if (films == null || films.isEmpty()) {
            return new ArrayList<>();
        }

        
        List<Film> copy = new ArrayList<>(films);   //Crear una copia para no modificar la lista original
        Collections.shuffle(copy);                  //Ordenar aleatoriamente

        // Devolver los primeros 10 elementos (o menos si hay menos de 10)
        int end = Math.min(10, copy.size());
        return new ArrayList<>(copy.subList(0, end));
    }
}