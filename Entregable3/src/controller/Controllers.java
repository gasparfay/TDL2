package controller;

import exceptions.*;
import java.awt.Image;
import java.util.List;
import javax.swing.*;
import model.*;
import service.*;
import view.*;




public class Controllers {
	private Operations ops;
	private Account activeAccount;
	private List<Profile> activeProfiles;
	private int activeProfile;
	private List<Film> loadedFilms;
	private List<Film> filmsToDisplay; 
	private MenuGUI menuGUI;
	
	public Controllers(Operations ops) {
		this.ops=ops;
		loadedFilms=null; 
		activeAccount=null;	
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
		int profileIndex = 0;
    	for (int i = 0; i < activeProfiles.size(); i++) {
    	    if (activeProfiles.get(i).getName().equals(name)) {
    	        profileIndex = i;
    	        break; 
    	    }
    	}

    	this.activeProfile = profileIndex;
		final boolean firstAcces;
		if(activeProfiles.get(activeProfile).getNeverLogIn()){
			ops.setProfileLogIn(activeProfiles.get(activeProfile));  //Indicamos que ya se logeo por primera vez
			firstAcces = true;
		} else{
			firstAcces = false;
		}
		
    	// THREAD 1 Se encarga de cargar los datos
    	Thread loaderThread = new Thread(() -> {
			try{
				List<Film> allFilms;
				
				if (ops.isFilmTableEmpty()) {
    	    	    ReadCSVFile reader = new ReadCSVFile();  
    	    	    allFilms = reader.readMoviesFromCSV();	 // Leer desde CSV
    	    	    ops.loadFilmsInBatch(allFilms);			 //Cargar a la BD
    	    	    
    	    	    this.loadedFilms = allFilms;			//Guardar en memoria
					if(firstAcces)     		  										  //Si es su primer acceso
    	    	    	this.filmsToDisplay = ops.getTop10Films(this.loadedFilms);	 //Cargamos las 10 mejores rankeadas
					else															//Si ya accedio antes
						this.filmsToDisplay = ops.get10RandomFilms(this.loadedFilms, activeProfiles.get(activeProfile));  //Cargamos 10 randoms
				
				} else if(this.loadedFilms == null) {
					allFilms = ops.getAllFilms();		// Cargar desde la BD
					this.loadedFilms = allFilms;
					if(firstAcces) 
    	    	    	this.filmsToDisplay = ops.getTop10Films(this.loadedFilms);
					else
						this.filmsToDisplay = ops.get10RandomFilms(this.loadedFilms, activeProfiles.get(activeProfile));
				
				} else {
					if(firstAcces) 
    	    	    	this.filmsToDisplay = ops.getTop10Films(this.loadedFilms);
					else
						this.filmsToDisplay = ops.get10RandomFilms(this.loadedFilms, activeProfiles.get(activeProfile));
				
				}
				loadImagePosters(this.filmsToDisplay);
				
			} catch(Exception e){
				e.printStackTrace();
				this.loadedFilms = null;
			}
    	});

    	// THREAD 2 Muestra la pantalla de carga hasta que termine el thread 1
    	Thread loadingThread = new Thread(() -> {
			
            LoadingGUI loadingGUI = new LoadingGUI();
            this.attachCloseEvent(loadingGUI);
            loadingGUI.setVisible(true);

            try {
                // Esperar a que termine la carga
                loaderThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                loadedFilms = null;
            }

            if (loadedFilms == null || loadedFilms.isEmpty()) {
                // Algo salió mal: mostrar error y volver al login
				loadingGUI.dispose();
                ErrorGUI errorGUI = new ErrorGUI("Error al cargar los datos. Intente nuevamente más tarde.", this);
                errorGUI.setVisible(true);
                setActiveAccount(null);
                return;
            }

            // Cambiar a la pantalla principal
            loadingGUI.dispose();
            this.menuGUI = new MenuGUI(this, filmsToDisplay, activeProfiles.get(activeProfile).getName());
            this.attachCloseEvent(this.menuGUI);
            this.menuGUI.setVisible(true);
        });

    	loaderThread.start();
    	loadingThread.start();
	}

	//Carga las imagenes de los posters desde las URLs, y las escala
	private void loadImagePosters(List<Film> films) {
		for (Film film : films) {
			String url = film.getPosterUrl();
			if (url != null && !url.isEmpty()) {
				ImageIcon posterImage = null;
				try {
					java.net.URL imageUrl = new java.net.URL(url);
					posterImage = new ImageIcon(imageUrl);
					Image scaled = posterImage.getImage().getScaledInstance(146, 200, Image.SCALE_SMOOTH);
					film.setPosterImage(new ImageIcon(scaled));
				} catch (Exception e) {
					System.err.println("Error al cargar la imagen desde: " + url + " - " + e.getMessage());
				}
			}else{
				film.setPosterImage(new ImageIcon(getClass().getResource("/media/loginImage.jpeg")));
			}
		}
	}

	public void handleLogout() {
		setActiveAccount(null);
		showLogin();
	}

	public void showRateMovieGUI(Film film, int index){
		RateMovieGUI ratingGUI = new RateMovieGUI(this, film, activeProfiles.get(activeProfile),index);
		ratingGUI.setVisible(true);
	}

	public void handleSaveReview(int rating, String reviewText, Profile profile, Film film, int index) {
		Review newReview = new Review(Rating.values()[rating-1], reviewText, new java.util.Date(), profile, film);
		ops.addReview(newReview);
		this.menuGUI.disableButton(index);
	}

	public void handleSearch(String title) {
		FilmConsultationOMDb filmConsultant = new FilmConsultationOMDb();
		Film film;
		try{
			film =  filmConsultant.searchFilm(title);
		} catch (APIException e){
			JOptionPane.showMessageDialog(this.menuGUI,"La pelicula " + title +" no se encuentra disponible",  "Pelicula no encontrada",JOptionPane.ERROR_MESSAGE);
			return;
		}
		MovieInfoGUI movieInfoGUI = new MovieInfoGUI(this, film);
		movieInfoGUI.setVisible(true);
	}

	public Account getActiveAccount() {
		return this.activeAccount;
	}
	
	public List<Film> handleSort( List<Film> films, String criterio){
        if("Title".equals(criterio))
            films.sort(new model.FilmComparatorTitle());
        else if("Genre".equals(criterio))
            films.sort(new model.FilmComparatorGenre());
        return films;
    }
	
	public List<Profile> getProfiles(){
		activeProfiles = ops.getProfiles(activeAccount);
		return activeProfiles;
	}
	
	public void setActiveAccount(Account activeAccount) {
		this.activeAccount = activeAccount;
	}

	public void attachCloseEvent(JFrame ventana) {
		//Este metdodo termina el programa al cerrar una ventana, y cierra la conexion con la BD
    	ventana.addWindowListener(new java.awt.event.WindowAdapter() {
       		 @Override
       		 public void windowClosing(java.awt.event.WindowEvent e) {
            	Main.finishProgram();
        	}
    	});
	}

	
    
}