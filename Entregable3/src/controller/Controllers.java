package controller;

import exceptions.*;
import java.util.List;
import javax.swing.*;
import model.*;
import service.MyConnection;
import service.Operations;
import view.*;


public class Controllers {
	private Operations ops;
	private Account activeAccount;
	private List<Profile> activeProfiles;
	private int activeProfile;
	
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
		ProfileSelectionGUI profilesGUI = new ProfileSelectionGUI(this);
		this.attachCloseEvent(profilesGUI);
    	profilesGUI.setVisible(true);
	}

	public void showRegister() {
		RegisterGUI registerGUI = new RegisterGUI(this);
		this.attachCloseEvent(registerGUI);
		registerGUI.setVisible(true);
	}
	
	public void handleRegister(RegisterGUI registerGUI, String email, String password) throws RegisterException {
		if (email.isEmpty() || password.isEmpty()) {
        	throw new RegisterException("Por favor, complete todos los campos.");
    	}	
    	if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
    	    throw new RegisterException("El formato de email no es válido.");
    	}
    	if (password.length() < 6) {
    	    throw new RegisterException("La contraseña debe tener al menos 6 caracteres.");
    	}
    	if (ops.existsAccount(email)) {
    	    throw new RegisterException("El email ya está registrado.");
    	}
    	Account newAccount = new Account(email, password);
    	ops.addAccount(newAccount);
	}

	public void showCreateProfile(ProfileSelectionGUI parent) {
		CreateProfileGUI dialog = new CreateProfileGUI(parent, this);
    	dialog.setVisible(true);
    	parent.reloadProfiles();
	}

	public void handleCreateProfile(String nombre) throws ProfileException {
		if(nombre.isEmpty()){
			throw new ProfileException("El nombre no puede estar vacio");
		}
		Profile newProfile = new Profile(nombre);
		newProfile.setAccount(activeAccount);
		ops.addProfile(newProfile);
	}

	public void enterWithProfile(String name){
		activeProfile = activeProfiles.indexOf(name);
		WelcomeGUI welcomeGUI = new WelcomeGUI();
		this.attachCloseEvent(welcomeGUI);
		welcomeGUI.setVisible(true);
	}

	public Account getActiveAccount() {
		return this.activeAccount;
	}
	
	public List<Profile> getProfiles(){
		activeProfiles = ops.getProfiles(activeAccount);
		return activeProfiles;
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