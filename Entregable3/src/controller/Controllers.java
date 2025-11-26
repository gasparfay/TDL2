package controller;

import exceptions.*;
import javax.swing.JFrame;
import model.*;
import service.MyConnection;
import service.Operations;
import view.*;


public class Controllers {
	private Operations ops;
	private Account activeAccount;
	
	public Controllers(Operations ops) {
		this.ops=ops;
	}

	public void showLogin() {
        LoginGUI loginGUI = new LoginGUI(this);
		this.attachCloseEvent(loginGUI);
        loginGUI.setVisible(true);
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

	public void succesLogin() {
		LoadingGUI loading = new LoadingGUI();
		this.attachCloseEvent(loading);
    	loading.setVisible(true);

    	//List<Film> filmsToShow;
//
    	//if (ops.isFirstAccess(activeAccount)) {
    	//    ops.loadFIlms();
    	//    filmsToShow = ops.getInitial10Films();
    	//    ops.markFirstAccessDone(activeAccount);
//
    	//} else {
    	//    filmsToShow = ops.get10RandomFilms(activeAccount);
//		
		WelcomeGUI welcome = new WelcomeGUI();
 		//WelcomeGUI welcome = new WelcomeGUI(this, activeAccount, filmsToShow);
    	//welcome.setVisible(true);
	}

	public void showRegister() {
		RegisterGUI registerGUI = new RegisterGUI(this);
		this.attachCloseEvent(registerGUI);
		registerGUI.setVisible(true);
	}
	public void handleRegister(RegisterGUI registerGUI, String nombres, String email, String password) throws RegisterException {
		if (nombres.isEmpty() || email.isEmpty() || password.isEmpty()) {
			throw new RegisterException("Por favor, complete todos los campos.");
		}
		else {
			if (ops.existsAccount(email)) {
				throw new RegisterException("El email ya está registrado.");
			} else {
				Account newAccount = new Account(email, password);
				Profile newProfile = new Profile(nombres);
				newProfile.setAccount(newAccount);
				ops.addAccount(newAccount);
			}
		}
	}

	public Account getActiveAccount() {
		return this.activeAccount;
	}
	
	public void setActiveAccount(Account activeAccount) {
		this.activeAccount = activeAccount;
	}

	public void attachCloseEvent(JFrame ventana) {
		//Este metdodo cierra la conexin a la base de datos al cerrar la ventana que esta activa
    	ventana.addWindowListener(new java.awt.event.WindowAdapter() {
       		 @Override
       		 public void windowClosing(java.awt.event.WindowEvent e) {
            	MyConnection.disconnect();
        	}
    	});
	}

	
    
}