package service;
import java.sql.*;
import java.util.Scanner;
import model.Account;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
		    MyConnection.disconnect();
        } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }

    public void ingresoDeDatos(Scanner in){
        Account account;
        account= crearCuenta(in);
    }

    public Account crearCuenta(Scanner in){
        String regex = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|yahoo)\\.com$"; //Solo acepta dominios comunes
        String email;
        String password;
        do{
        System.out.println("Registro de usuario");
        System.out.println();
        do {
            System.out.print("Ingrese su email:");
            email = in.next();
            System.out.println();
            if (!email.matches(regex)) {
                System.out.println("Formato de email inválido. Intente nuevamente.");
            }
        } while (!email.matches(regex)); 
        System.out.println();
        System.out.print("Ingrese su contraseña:");
        password = in.next();
        System.out.println();
        System.out.println("Sus datos son los correctos? (S/N)");
        } while (in.next().equalsIgnoreCase("N"));
        return new Account(email, password);
    }
}   
