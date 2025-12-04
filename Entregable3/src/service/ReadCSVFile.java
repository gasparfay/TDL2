package service;

import java.io.*;
import java.time.Duration;
import java.util.*;
import model.Film;
import model.Genre;

public class ReadCSVFile {
    public List<Film> readMoviesFromCSV() {
        String path="data/movies_database.csv";
        List<Film> films = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // leer encabezado
            line = br.readLine();        // primera fila de datos

            while (line != null) {

                String[] fields = splitCSV(line);

                //En el caso de que haya una fila mal formateada la salteamos
                if (fields.length < 9) {
                    line = br.readLine();
                    continue;
                }

                try {
                    // Release_Date es "YYYY-MM-DD", extraer solo el año
                    int releaseYear = Integer.parseInt(fields[0].trim().split("-")[0]);
                    String title = fields[1].trim();
                    String overview = fields[2].trim();   
                    float voteAverage = Float.parseFloat(fields[5].trim());
                    String genresRaw = fields[7].trim();
                    String posterUrl = fields[8].trim();

                    //Datos del modelo que no están en el CSV, los dejamos con un valor default
                    String director = "Desconocido";
                    Duration duration = Duration.ofMinutes(0);

                    //Nos quedamos con el primer genero
                    Genre genre = mapGenre(getFirstGenre(genresRaw));

                    Film film = new Film(title,director,duration,genre,releaseYear,posterUrl,voteAverage);

                    film.setSynopsis(overview);
                    films.add(film);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Advertencia: Fila ignorada - " + e.getMessage());
                }

                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }

        return films;
    }

    private String[] splitCSV(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (c == ',' && !insideQuotes) {
                // Fin de campo: agregar y resetear
                fields.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        
        // Agregar el último campo
        fields.add(sb.toString().trim());
        return fields.toArray(new String[0]);
    }

    private String getFirstGenre(String genreField) {
        if (genreField == null || genreField.isEmpty()) 
            return "Other";

        genreField = genreField.replace("\"", "").trim();
        String[] parts = genreField.split(",");

        if (parts.length == 0) return "Other";

        return parts[0].trim();
    }

    private Genre mapGenre(String genreStr) {
        if (genreStr == null) return Genre.OTRO;

        switch (genreStr.trim().toLowerCase()) {

            case "action":
                return Genre.ACCION;

            case "adventure":
                return Genre.AVENTURA;

            case "animation":
                return Genre.ANIMACION;

            case "comedy":
                return Genre.COMEDIA;

            case "crime":
                return Genre.CRIMEN;

            case "drama":
                return Genre.DRAMA;

            case "fantasy":
                return Genre.FANTASIA;

            case "history":
                return Genre.HISTORIA;

            case "horror":
                return Genre.TERROR;

            case "music":
                return Genre.MUSICA;

            case "mystery":
                return Genre.MISTERIO;

            case "romance":
                return Genre.ROMANCE;

            case "war":
                return Genre.GUERRA;

            case "family":
                return Genre.INFANTIL;

            case "sport":
                return Genre.DEPORTE;

            case "documentary":
                return Genre.DOCUMENTAL;

            case "science fiction":
                return Genre.CIENCIA_FICCION;
            default:
                return Genre.OTRO;
        }
    }
}
