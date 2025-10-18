package service;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            con.createStatement().execute("CREATE TABLE IF NOT EXISTS moneda(nombre TEXT, valor REAL);");
            con.createStatement().executeUpdate("INSERT INTO moneda(nombre, valor) VALUES('Bitcoin', 67000);");
		    MyConnection.disconnect();
        } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }
}   
