package service;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
            Scanner in = new Scanner(System.in);
            Operations ops = new Operations();
            int opcion;
            do{
                mostrarMenuPrincipal();
                opcion = in.nextInt();
                in.nextLine(); 
                switch(opcion){
                    case 1:
                        ops.profileRegistration(in);
                        break;
                    case 2:
                        ops.accountRegistration(in);
                        break;
                    case 3:
                        ops.filmRegistration(in);
                        break;
                    case 4:
                        ops.showAccounts(in);
                        break;
                    case 5:
                        ops.showFilms(in);
                        break;
                    case 6:
                        ops.reviewRegistration(in);
                        break;
                    case 7:
                        //aprobarResenia(in);
                        break;
                    case 8:
                        System.out.print("Fin del programa");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            }while(opcion!=8);
            in.close();
		    MyConnection.disconnect();
        } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }


    public static void mostrarMenuPrincipal() {
        System.out.println("\n--------------------------------------------");
        System.out.println("- Ingrese la operación que desee realizar -");
        System.out.println("1. Registrar perfil");
        System.out.println("2. Registrar nueva cuenta");
        System.out.println("3. Registrar película");
        System.out.println("4. Listar usuarios");
        System.out.println("5. Listar películas");
        System.out.println("6. Registrar reseña");
        System.out.println("7. Aprobar reseña");
        System.out.println("8. Salir");
        System.out.print("> ");
    }

    
}   
