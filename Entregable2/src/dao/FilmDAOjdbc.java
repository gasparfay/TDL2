package dao;

import java.sql.Connection;

import service.MyConnection;

public class FilmDAOjdbc implements FilmDAO {
    	private Connection con;
	
	    public ActivoDAOjdbc() {
	    	this.con = MyConnection.getConnection();
	    }

        @Override
        public void loadFilm(Film film) {
            // Implementación del método para cargar una película desde la base de datos
        }
        
        @Override
        public List<Film> findAll() {
            // Implementación del método para encontrar todas las películas en la base de datos
        }

}
