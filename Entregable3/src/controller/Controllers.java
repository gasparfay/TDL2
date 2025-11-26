package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import exceptions.*;
import model.*;
import service.Operations;
import view.*;

public class Controllers {
	private Operations ops;
	private Account activeAccount;
	
	public Controllers(Operations ops) {
		this.ops=ops;
	}
	
	public void showLogin(LoginGUI viewLogin) {
		viewLogin.setVisible(true);
	}

    public void handleLogin(LoginGUI viewLogin,String email,String password) throws LoginException {
        if (email.isEmpty() || password.isEmpty()) {
            throw new LoginException("Por favor, complete todos los campos.");
        }
        else {
            if (ops.existsAccount(email)) {
            	Account acc = ops.getAccount(email);
            	if(acc.getPassword().equals(password)) {
            		this.setActiveAccount(acc);
            	}
            	else {
            		throw new LoginException("Contraseña incorrecta!");
            	}
            } else {
            	throw new LoginException("Email no encontrado, registrese para ingresar.");
            }
        }
    }

    public void abrirVentanaLogin() {
        LoginGUI loginGUI = new LoginGUI(this);
        loginGUI.setVisible(true);
    }

	public Account getActiveAccount() {
		return activeAccount;
	}
	public void setActiveAccount(Account activeAccount) {
		this.activeAccount = activeAccount;
	}
    
}