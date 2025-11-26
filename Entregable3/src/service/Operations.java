package service;

import dao.*;
import java.util.Scanner;
import model.*;

public class Operations {
    private AccountDAO accountDAO;
    private ProfileDAO profileDAO;
    private FilmDAO filmDAO;
    private ReviewDAO reviewDAO;
    private Scanner in;

    public Operations(Scanner in) {
        this.accountDAO = new AccountDAOjdbc(MyConnection.getConnection());
        this.profileDAO = new ProfileDAOjdbc(MyConnection.getConnection());
        this.filmDAO = new FilmDAOjdbc(MyConnection.getConnection());
        this.reviewDAO = new ReviewDAOjdbc(MyConnection.getConnection());
        this.in = in;
    }

    public boolean existsAccount(String email) {
        return accountDAO.emailExists(email);
    }

    public Account getAccount(String email) {
        return accountDAO.findByEmail(email);
    }

    //public void profileRegistration() {
    //    String name;
    //    boolean validName;
    //    
    //    do {
    //        System.out.println("\nRegistro de perfil");
    //        System.out.println();
    //        do {
    //            System.out.print("Ingrese el nombre del perfil (solo letras): ");
    //            name = in.nextLine();
//
    //            validName = name.matches("[a-zA-Z]+");
    //            
    //            if (!validName) {
    //                System.out.println("Error: El nombre solo puede contener letras.");
    //                System.out.println();
    //            }
    //        } while (!validName);
//
    //        System.out.println();
    //        System.out.println("Los datos son correctos? (S/n)");
    //    } while (in.nextLine().equals("n"));
//
    //    Profile profile = new Profile(name);
    //    if (profileDAO.loadProfile(profile)) {
    //        System.out.println("Perfil registrado exitosamente!");
    //    } else {
    //        System.out.println("Error al registrar el perfil.");
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}
    //
    //public void accountRegistration() {
    //    System.out.println("\nRegistro de usuario");
    //    System.out.println();
//
    //    // Mostrar perfiles disponibles
    //    List<Profile> profiles = profileDAO.findAll();
    //    if (profiles.isEmpty()) {
    //        System.out.println("No hay perfiles registrados en el sistema.");
    //        System.out.println("Por favor, registre un perfil primero.");
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }
//
    //    System.out.println("Perfiles disponibles:");
    //    for (int i = 0; i < profiles.size(); i++) {
    //        System.out.printf("%d. %s%n", i + 1, profiles.get(i).getName());
    //    }
//
    //    // Selección del perfil
    //    int profileChoice;
    //    do {
    //        System.out.print("\nSeleccione el número del perfil que desea asociar a su cuenta: ");
    //        while (!in.hasNextInt()) {
    //            System.out.println("Por favor, ingrese un número válido.");
    //            in.next();
    //        }
    //        profileChoice = in.nextInt();
    //    } while (profileChoice < 1 || profileChoice > profiles.size());
//
    //    Profile prof = profiles.get(profileChoice - 1);
    //    System.out.println("Perfil seleccionado: " + prof.getName());
    //    System.out.println();
//
    //    // Ingreso de datos de la cuenta
    //    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    //    String email;
    //    String password;
//
    //    do {
    //        boolean exist;
    //        boolean format;
    //        do {
    //            exist=false;
    //            format=true;
    //            System.out.print("Ingrese su email: ");
    //            email = in.next();
    //            System.out.println();
    //            if (!email.matches(regex)) {
    //                System.out.println("Formato de email inválido. Intente nuevamente.");
    //                format=false;
    //                continue;
    //            }
//
    //            if (accountDAO.emailExists(email)) {
    //                System.out.println("Este email ya está registrado. Por favor, use otro email.");
    //                exist=true;
    //                continue;
    //            }
    //        } while (!format || exist);
//
    //        System.out.print("Ingrese su contraseña: ");
    //        password = in.next();
    //        System.out.println();
    //        System.out.println("Sus datos son los correctos? (S/n)");
    //    } while (in.nextLine().equals("n"));
//
    //    Account acc = new Account(email, password);
    //    
    //    if(accountDAO.loadAccount(acc)) {
    //        profileDAO.associateWithAccount(prof, acc);
    //        System.out.println("Cuenta registrada exitosamente!");
    //        System.out.println("Perfil '" + prof.getName() + "' asociado a la cuenta.");
    //    } else {
    //        System.out.println("Error al registrar la cuenta.");
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}
//
    //public void filmRegistration() {
    //    System.out.println("\nRegistro de película");
    //    
    //    String title, director;
    //    double durationMinutes;
    //    Genre genre;
    //    
    //    do {
    //        System.out.print("\nIngrese el título de la película: ");
    //        title = in.nextLine();
    //        
    //        System.out.print("Ingrese el director: ");
    //        director = in.nextLine();
    //        
    //        do {
    //            System.out.print("Ingrese la duración en minutos: ");
    //            while (!in.hasNextDouble()) {
    //                System.out.println("\nPor favor, ingrese un número válido.\n");
    //                in.next();
    //            }
    //            durationMinutes = in.nextDouble();
    //            if (durationMinutes <= 0) {
    //                System.out.println("\nLa duración debe ser mayor a 0 minutos.\n");
    //            }
    //        } while (durationMinutes <= 0);
    //        in.nextLine(); 
    //        
    //        System.out.println("Géneros disponibles:");
    //        for (Genre g : Genre.values()) {
    //            System.out.println("- " + g.name());
    //        }
    //        
    //        Genre selectedGenre = null;
    //        do {
    //            System.out.print("Ingrese el género: ");
    //            String genreInput = in.nextLine().toUpperCase();
    //            try {
    //                selectedGenre = Genre.valueOf(genreInput);
    //            } catch (IllegalArgumentException e) {
    //                System.out.println("Género no válido. Por favor, seleccione uno de la lista.");
    //            }
    //        } while (selectedGenre == null);
    //        
    //        genre = selectedGenre;
    //        
    //        System.out.println("\nResumen de la película:");
    //        System.out.println("Título: " + title);
    //        System.out.println("Director: " + director);
    //        System.out.println("Duración: " + durationMinutes + " minutos");
    //        System.out.println("Género: " + genre);
    //        System.out.println("\n¿Los datos son correctos? (S/n)");
    //        
    //    } while (in.nextLine().equals("n"));
    //    
    //    Film film = new Film(title, director, Duration.ofMinutes((long)durationMinutes), genre);
    //    
    //    if (filmDAO.loadFilm(film)) {
    //        System.out.println("Película registrada exitosamente!");
    //    } else {
    //        System.out.println("Error al registrar la película.");
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}
    //
    //public void showAccounts(){
    //    List<Account> accounts = accountDAO.findAll();
    //    if (accounts==null) {
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }
    //    if (accounts.isEmpty()) {
    //        System.out.println("\nNo hay cuentas registradas en el sistema.");
    //        System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //        return;
    //    }
//
    //    int option;
//
    //    do {
    //        System.out.println("\nSeleccione el criterio de ordenamiento:");
    //        System.out.println("1. Ordenar por email");
    //        System.out.println("2. Ordenar por ID");
    //        System.out.print("Ingrese su opción (1-2): ");
//
    //        option = in.nextInt();
    //        in.nextLine();
//
    //        if (option < 1 || option > 2) {
    //            System.out.println("\nOpción inválida. Intente nuevamente.\n");
    //        }
//
    //    } while (option < 1 || option > 2);
//
    //    if (option == 1) {
    //        accounts.sort(new AccountComparatorEmail());
    //    } else {
    //        // Ordenar por ID (Ya vienen asi de la BD)
    //    }
//
    //    System.out.println("\nCuentas registradas:");
    //    String format = "%-5s   %-95s%n";
    //    System.out.printf(format, "ID", "Email");
    //    System.out.println("-".repeat(100));
    //    for (Account acc : accounts) {
    //        System.out.printf(format, acc.getAccId(), acc.getEmail());
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}
//
    //public void showFilms() {
    //    List<Film> films = filmDAO.findAll();
    //    if (films == null) {
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }
    //    if (films.isEmpty()) {
    //        System.out.println("\nNo hay películas registradas en el sistema.");
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }
//
    //int option;
//
    //do {
    //    System.out.println("\nSeleccione el criterio de ordenamiento:");
    //    System.out.println("1. Ordenar por título");
    //    System.out.println("2. Ordenar por duración");
    //    System.out.println("3. Ordenar por género");
    //    System.out.println("4. Ordenar por ID");
    //    System.out.print("Ingrese su opción (1-4): ");
//
    //    option = in.nextInt();
    //    in.nextLine();
//
    //    if (option < 1 || option > 4) {
    //        System.out.println("\nOpción inválida. Intente nuevamente.\n");
    //    }
//
    //} while (option < 1 || option > 4);
//
    //switch (option) {
    //    case 1:
    //        films.sort(new FilmComparatorTitle());
    //        break;
    //    case 2:
    //        films.sort(new FilmComparatorDuration());
    //        break;
    //    case 3:
    //        films.sort(new FilmComparatorGenre());
    //        break;
    //    case 4:
    //        // Ordenar por ID
    //        break;
    //}
//
    //    System.out.println("\nPelículas registradas:");
    //    String format = "%-5s   %-30s   %-30s   %-15s   %-15s%n";
    //    System.out.printf(format, "ID", "Título", "Director", "Duración", "Género");
    //    System.out.println("-".repeat(100));
    //    for (Film film : films) {
    //        System.out.printf(format,
    //            film.getFilmId(),
    //            film.getTitle(),
    //            film.getDirector(),
    //            film.getDuration().toMinutes() + " mins",
    //            film.getGenre());
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}
//
    //public void reviewRegistration() {
    //    System.out.println("\nRegistro de reseña");
    //    
    //    //Pedir email y contraseña para identificar la cuenta
    //    String email, password; 
    //    Account acc;
    //    do{
    //        System.out.print("\nIngrese su email (0 para volver atras): ");
    //        email = in.nextLine();
    //        if(email.equals("0")){
    //            return;
    //        }
    //        System.out.print("Ingrese su contraseña: ");
    //        password = in.nextLine();
    //        acc = accountDAO.findByEmail(email);
    //        if ((acc == null)  || (!acc.getPassword().equals(password))) {
    //            System.out.println("Error: Email o contraseña incorrectos.");
    //        } 
    //    }while ((acc == null)  || (!acc.getPassword().equals(password)));
    //    System.out.println("Cuenta verificada. Puede proceder a ingresar la reseña.");
    //    System.out.println();
//
    //    //Listar peliculas disponibles y pedir que se elija una
    //    List<Film> films = filmDAO.findAll();
    //    if (films.isEmpty()) {
    //        System.out.println("\nNo hay películas registradas en el sistema.");
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }                   
    //    System.out.println("Películas disponibles:");
    //    for (int i = 0; i < films.size(); i++) {                
    //        System.out.printf("%d. %s%n", i + 1, films.get(i).getTitle());
    //    }   
    //    int filmChoice;
    //    do {
    //        System.out.print("\nSeleccione el número de la película que desea reseñar: ");
    //        while (!in.hasNextInt()) {
    //            System.out.println("Por favor, ingrese un número válido, presione enter para intentar nuevamente.");
    //            in.nextLine();
    //        }
    //        filmChoice = in.nextInt();
    //    } while (filmChoice < 1 || filmChoice > films.size());  
    //    in.nextLine();
//
    //    Film film = films.get(filmChoice - 1);
    //    System.out.println("Película seleccionada: " + film.getTitle());
//
    //    // Ingreso de datos de la reseña
    //    Rating rating;
    //    String text;
    //    Date creationDate;
    //    do {
    //        creationDate=new Date();
    //        System.out.print("Ingrese el texto de la reseña: ");
    //        text = in.nextLine();
    //        
    //        System.out.println("Calificaciones disponibles:");
    //        for (Rating r : Rating.values()) {
    //            System.out.println("- " + r.name());
    //        }
//
    //        do {
    //            System.out.print("Ingrese la calificación de la reseña: ");
    //            String ratingInput = in.nextLine().toUpperCase();
    //            try {
    //                rating = Rating.valueOf(ratingInput);
    //            } catch (IllegalArgumentException e) {
    //                rating = null;
    //                System.out.println("Calificación no válida. Por favor, seleccione una de la lista.");
    //            }
    //        } while (rating == null);
    //        System.out.println();
    //        System.out.println("Resumen de la reseña:");
    //        System.out.println("Comentarios: " + text);
    //        System.out.println("Calificación: " + rating);
    //        System.out.println();
    //        System.out.println("Sus datos son los correctos? (S/n)");
    //    } while (in.nextLine().equals("n"));
//
//
    //    Review review = new Review(rating, text, creationDate, acc, film);
    //    if (reviewDAO.loadReview(review)) {
    //        System.out.println("Reseña registrada exitosamente!");
    //    } else {
    //        System.out.println("Error al registrar la reseña.");
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}
//
    //public void approveReview() {
    //    List<Review> pendingReviews = reviewDAO.findPending();
    //    if (pendingReviews == null) {
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }
    //    if (pendingReviews.isEmpty()) {
    //        System.out.println("\nNo hay reseñas pendientes de aprobación.");
    //        System.out.println("Presione Enter para volver al menu principal.");
    //        in.nextLine();
    //        return;
    //    }
//
    //    System.out.println("\nReseñas pendientes de aprobación:");
    //    for (int i = 0; i < pendingReviews.size(); i++) {
    //        Review rev = pendingReviews.get(i);
    //        System.out.printf("%d. Película: %s | Cuenta: %s | Calificación: %s | Texto: %s%n",
    //            i + 1,
    //            rev.getFilm().getTitle(),
    //            rev.getAccount().getEmail(),
    //            rev.getRating().name(),
    //            rev.getText());
    //    }
//
    //    int reviewChoice;
    //    do {
    //        System.out.print("\nSeleccione el número de la reseña que desea aprobar/rechazar: ");
    //        while (!in.hasNextInt()) {
    //            System.out.println("Por favor, ingrese un número válido.");
    //            in.next();
    //        }
    //        reviewChoice = in.nextInt();
    //    } while (reviewChoice < 1 || reviewChoice > pendingReviews.size());
    //    in.nextLine();
//
    //    Review selectedReview = pendingReviews.get(reviewChoice - 1);
//
    //    String decision;
//
    //    do {
    //        System.out.print("¿Desea aprobar (A) o rechazar (R) la reseña? ");
    //        decision = in.nextLine().trim().toUpperCase();
//
    //        if (!decision.equals("A") && !decision.equals("R")) {
    //            System.out.println("Decisión no válida. Intente nuevamente.\n");
    //        }
//
    //    } while (!decision.equals("A") && !decision.equals("R"));
//
    //    if (decision.equals("A")) {
    //        selectedReview.setStatus(ReviewStatus.APPROVED);
    //    } else {
    //        selectedReview.setStatus(ReviewStatus.REJECTED);
    //    }
//
    //    if (reviewDAO.modifyStatus(selectedReview)) {
    //        System.out.println("Reseña actualizada exitosamente!");
    //    } else {
    //        System.out.println("Error al actualizar la reseña.");
    //    }
    //    System.out.println("Presione Enter para volver al menu principal.");
    //    in.nextLine();
    //}


}