package service;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
		    MyConnection.disconnect();
        } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }
}   
