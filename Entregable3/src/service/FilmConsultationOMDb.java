package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration; 

import org.json.JSONObject;

import exceptions.APIException;
import model.Film;
import model.Genre;

public class FilmConsultationOMDb {
private static final String API_KEY = "6420aa6c"; // Tu API Key
    private static final String BASE_URL = "https://www.omdbapi.com/?apikey=" + API_KEY + "&t=";

    // Ahora devuelve un objeto FILM directamente
    public Film searchFilm(String titulo) throws APIException {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new APIException("El título no puede estar vacío.");
            }

            String url = BASE_URL + titulo.replace(" ", "+");
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());

            if (json.has("Response") && "False".equalsIgnoreCase(json.getString("Response"))) {
                throw new APIException("Película no encontrada: " + titulo);
            }

            // --- EXTRACCIÓN DE DATOS ---
            String title = json.optString("Title", "Desconocido");
            String director = json.optString("Director", "Desconocido");
            String plot = json.optString("Plot", "Sin sinopsis");
            String poster = json.optString("Poster", "");
            
            // Parseo de Año
            String yearStr = json.optString("Year", "0").replaceAll("\\D", "");
            int year = 0;
            try { year = Integer.parseInt(yearStr); } catch (Exception e) {}

            // Parseo de Rating
            String ratingStr = json.optString("imdbRating", "0.0");
            float rating = 0.0f;
            try { rating = Float.parseFloat(ratingStr); } catch (Exception e) {}

            // --- LA PARTE DIFÍCIL: CONVERTIR DATOS DE API A TUS OBJETOS ---
            
            // 1. Duration: La API devuelve "140 min". Hay que convertirlo a java.time.Duration
            String runtimeStr = json.optString("Runtime", "0").split(" ")[0]; // Tomamos solo el número
            long minutes = 0;
            try { minutes = Long.parseLong(runtimeStr); } catch (Exception e) {}
            Duration duration = Duration.ofMinutes(minutes);

            // 2. Genre: La API devuelve "Action, Adventure". Tu clase pide UN objeto Genre.
            // Crearemos un Genre genérico con el primer género que venga o "Unknown".
            String genreText = json.optString("Genre", "Unknown").split(",")[0]; 
            Genre genre = new Genre(); // Asumiendo que tienes un constructor vacío o simple
            genre.setDescription(genreText); // O el setter que tengas en Genre

            // --- CREAR EL OBJETO FILM ---
            // Usamos tu constructor: Film(title, director, duration, genre)
            Film film = new Film(title, director, duration, genre);
            
            // Seteamos los datos extra que pide el entregable usando los setters nuevos
            film.setSynopsis(plot);
            film.setReleaseYear(year);     // Campo nuevo
            film.setPosterUrl(poster);     // Campo nuevo
            film.setAverageRating(rating); // Campo nuevo

            return film;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new APIException("Búsqueda interrumpida", e);
        } catch (Exception e) {
            throw new APIException("Error conectando a OMDb: " + e.getMessage(), e);
        }
    }

