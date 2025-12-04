package service;
import controller.Controllers;
import java.sql.*;


public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
            Operations ops = new Operations();
            Controllers controller = new Controllers(ops);

			controller.showLogin();
         } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }   
}