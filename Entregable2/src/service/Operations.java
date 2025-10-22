package service;

import dao.*;
import java.util.Scanner;
import model.*;   

public class Operations {
    private AccountDAO accountDAO;

    public Operations() {
        this.accountDAO = new AccountDAOjdbc(MyConnection.getConnection());
    }


    public  void accountRegister(Scanner in) {
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

        Account acc=new Account(email, password);
        accountDAO.loadAccount(acc);
    }
}