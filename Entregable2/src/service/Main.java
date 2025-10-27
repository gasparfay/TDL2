package service;
import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
	    try {
            Connection con = MyConnection.getConnection(); 
            TableCreator.createTables(con);
            Scanner in = new Scanner(System.in);
            Operations ops = new Operations(in);
            int opcion = 0;

            do {
                mostrarMenuPrincipal();
            
                if (in.hasNextInt()) {
                    opcion = in.nextInt();
                    in.nextLine(); // limpiar buffer
                
                    switch (opcion) {
                        case 1:
                            ops.profileRegistration();
                            break;
                        case 2:
                            ops.accountRegistration();
                            break;
                        case 3:
                            ops.filmRegistration();
                            break;
                        case 4:
                            ops.showAccounts();
                            break;
                        case 5:
                            ops.showFilms();
                            break;
                        case 6:
                            ops.reviewRegistration();
                            break;
                        case 7:
                            ops.approveReview();
                            break;
                        case 8:
                            System.out.println("Fin del programa");
                            break;
                        default:
                            System.out.println("Opción inválida. Presione enter para intentar nuevamente.");
                            in.nextLine();
                            break;
                    }

                } else {
                    System.out.println("Entrada no válida. Presione enter para intentar nuevamente.");
                    in.nextLine(); 
                    in.nextLine();
                }
            
            } while (opcion != 8);
            in.close();
		    MyConnection.disconnect();
        } catch (SQLException e) {  
            System.out.print("Error en la operación con la BD: " + e.getMessage());
        }
    }


    public static void mostrarMenuPrincipal() {
        System.out.print("\033[H\033[2J");//LIMPIA LA CONSOLA
        System.out.flush();                 //ACTUALIZA LA CONSOLA
        System.out.println("Bienvenido a la plataforma de streaming TDL2_G8");
        System.out.println("-".repeat(100));
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
