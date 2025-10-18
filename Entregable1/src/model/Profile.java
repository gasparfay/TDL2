package model;

import java.util.UUID;

/**
 * Representa un perfil de usuario en la plataforma de streaming.
 * Contiene información como el identificador único, nombre y preferencias del perfil.
 *
 * @author Grupo 8
 * @version 1.0
 * @see Account
 */
public class Profile {
    /**
     * Identificador único del perfil.
     */
    private UUID profileId;

    /**
     * Nombre del perfil.
     */
    private String name;

    /**
     * Idioma preferido del perfil.
     * El enum Language está ubicado en el diagrama UML de clases.
     */
    // private Language lang;

    /**
     * Géneros favoritos del perfil.
     * El enum Genre está ubicado en el diagrama UML de clases.
     */
    // private EnumSet<Genre> genres;

    /**
     * Historial de visualizaciones del perfil. Ubicado en el diagrama UML de clases.
     * Relación: Un Profile tiene un historial de visualizaciones.
     */
    //private WatchHistory watchHistory;

     /**
      * Lista de reviews del perfil. Ubicado en el diagrama UML de clases.
      * Relación: Un Profile puede escribir múltiples reviews.
      */
     //private List<Review> profileReviews;

    /**
     * Estadisticas del perfil. Ubicado en el diagrama UML de clases.
     * Relación: Un Profile tiene asociadas sus estadísticas de visualización y géneros favoritos.
     */
    // private ProfileStats stats;

    /**
     * Listas personalizadas de contenidos con un nombre asociado. Ubicado en el diagrama UML de clases.
     * Relación: Un Profile puede tener múltiples listas personalizadas.
     */
    // private List<CustomContentList> customLists;

    /**
     * Lista ,con un nombre asociado, de contenido sugerido basada en sus preferencias y hábitos de visualización. 
     * Ubicado en el diagrama UML de clases.
     */
    // private CustomContentList suggestedContentList;

	/**
     * Crea un nuevo perfil con el nombre y el idioma especificados.
     * Inicializa el identificador único del perfil.
     *
     * @param name nombre del perfil
     */

	public Profile(String name /*, Language lang*/) {
	//  this.watchHistory = new WatchHistory();
	//  this.profileReviews = new LinkedList<Review>();
	//  this.stats = new ProfileStats();
	//  this.customLists = new LinkedList<CustomContentList>();
	//  this.suggestedContentList = new CustomContentList();
        this.profileId = UUID.randomUUID();
        this.name = name;
    //  this.lang = lang;
    //  this.genres = EnumSet.noneOf(Genre.class);
    }

	/**
	 * Obtiene el identificador único del perfil.
	 * 
	 * @return el identificador único del perfil
	 */
	public UUID getProfileId() {
		return profileId;
	}

	/**
	 * Obtiene el nombre del perfil.
	 * 
	 * @return el nombre del perfil
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del perfil.
	 * 
	 * @param name el nuevo nombre del perfil
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene el idioma preferido del perfil.
	 * 
	 * @return idioma del perfil
	 */
	/*
	 * public Language getLang() {
	 * return lang;
	 * }
	 */

	/**
	 * Establece el idioma preferido del perfil.
	 * 
	 * @param lang nuevo idioma del perfil
	 */
	/*
	 * public void setLang(Language lang) {
	 * this.lang = lang;
	 * }
	 */

	/**
	 * Obtiene los géneros favoritos del perfil.
	 * 
	 * @return conjunto de géneros favoritos
	 */
	/*
	 * public EnumSet<Genre> getGenres() {
	 * return genres;
	 * }
	 */

	/**
	 * Establece los géneros favoritos del perfil.
	 * 
	 * @param genres nuevo conjunto de géneros favoritos
	 */
	/*
	 * public void setGenres(EnumSet<Genre> genres) {
	 * this.genres = genres;
	 * }
	 */

	/**
     * Obtiene el historial de contenido visualizdo por el perfil.
     * 
     * @return historial de contenido visualizado
     */
	/* 
    public WatchHistory getWatchHistory() {
        return watchHistory;
    }*/

    /**
     * Obtiene la lista de reviews del perfil.
     * 
     * @return lista de reviews
     */
    /*public List<Review> getProfileReviews() {
        return profileReviews;
    }*/



    /**
     * Obtiene las estadísticas del perfil.
     * 
     * @return estadísticas del perfil
     */
    /*public ProfileStats getStats() {
        return stats;
    }*/


    /**
     * Obtiene las listas personalizadas de contenidos del perfil.
     * 
     * @return listas personalizadas de contenidos
     */
    /*public List<CustomContentList> getCustomLists() {
        return customLists;
    }*/

    /**
     * Obtiene la lista de contenido sugerido para el perfil.
     * 
     * @return lista de contenido sugerido
     */
    /*public List<CustomContentContent> getSuggestedContentList() {
        return suggestedContentList;
    }*/

}
