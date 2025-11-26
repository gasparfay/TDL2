package service;
import controller.Controllers;
import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
            Scanner in = new Scanner(System.in);
            Operations ops = new Operations(in);

            Controllers controller = new Controllers(ops);
			
			controller.showLogin();
            in.close();
         } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }   
}
