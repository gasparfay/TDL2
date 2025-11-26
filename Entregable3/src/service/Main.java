package service;
import java.sql.*;
import java.util.Scanner;
import view.LoginGUI;
import controller.Controllers;


public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
            Scanner in = new Scanner(System.in);
            Operations ops = new Operations(in);

            Controllers controller = new Controllers(ops);
			LoginGUI loginView = new LoginGUI(controller);
			
			//controller.crearMonedasPrueba();
			controller.showLogin(loginView);
            in.close();
		    MyConnection.disconnect();
         } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }   
}
