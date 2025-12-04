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

    private static final String API_KEY = "6420aa6c";
    private static final String BASE_URL = "https://www.omdbapi.com/?apikey=" + API_KEY + "&t=";

    public Film searchFilm(String titulo) throws APIException {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new APIException("El título no puede estar vacío.");
            }

            String url = BASE_URL + titulo.replace(" ", "+");
            
            // 3. Conexión: Crear el cliente y la solicitud HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            
            // 4. Envío: Ejecutar la solicitud y obtener la respuesta como String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // 5. Procesamiento: Convertir la respuesta de texto a objeto JSON
            JSONObject json = new JSONObject(response.body());

            // 6. Verificación: Chequear si la API respondió con un error lógico
            if (json.has("Response") && "False".equalsIgnoreCase(json.getString("Response"))) {
                throw new APIException("Película no encontrada: " + titulo);
            }

            // 7. Extracción Básica: Obtener Strings usando optString para evitar nulos
            String title = json.optString("Title", "Desconocido");
            String director = json.optString("Director", "Desconocido");
            String plot = json.optString("Plot", "Sin sinopsis");
            String poster = json.optString("Poster", "");

            // 8. Parsing Numérico: Limpiar y convertir Año y Rating
            String yearStr = json.optString("Year", "0").replaceAll("\\D", "");
            int year = 0;
            try { year = Integer.parseInt(yearStr); } catch (Exception e) {}
            
            String ratingStr = json.optString("imdbRating", "0.0");
            float rating = 0.0f;
            try { rating = Float.parseFloat(ratingStr); } catch (Exception e) {}

            // 9. Conversión Compleja I: Transformar "140 min" a objeto Duration
            String runtimeStr = json.optString("Runtime", "0").split(" ")[0];
            long minutes = 0;
            try { minutes = Long.parseLong(runtimeStr); } catch (Exception e) {}
            Duration duration = Duration.ofMinutes(minutes);

            // 10. Conversión Compleja II: Transformar texto a Enum Genre
            String genreText = json.optString("Genre", "DRAMA").split(",")[0].trim();
            Genre genre;
            try {
                String enumName = genreText.toUpperCase().replace("-", "_").replace(" ", "_");
                genre = Genre.valueOf(enumName);
            } catch (IllegalArgumentException e) {
                genre = Genre.values()[0]; // Valor por defecto si falla
                System.out.println("Aviso: Se asignó género por defecto para: " + genreText);
            }

            // 11. Construcción: Crear el objeto Film con el constructor base
            Film film = new Film(title, director, duration, genre);

            // 12. Completado: Asignar los atributos adicionales requeridos por el TP
            film.setSynopsis(plot);
            film.setReleaseYear(year);
            film.setPosterUrl(poster);
            film.setAverageRating(rating/2); //Ajustamos de 0-10 a 0-5 estrellas

            return film;

        }catch (Exception e) {
                throw new APIException("Error conectando a OMDb: " + e.getMessage());
        }
    }
}

