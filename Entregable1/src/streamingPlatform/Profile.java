package streamingPlatform;

import java.util.UUID;

/**
 * Representa un perfil de usuario en la plataforma de streaming.
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
	 * Obtiene el identificador único del perfil.
	 * 
	 * @return profileId del perfil
	 */
	public UUID getProfileId() {
		return profileId;
	}

	/**
	 * Establece el identificador único del perfil.
	 * 
	 * @param profileId nuevo profileId del perfil
	 */
	public void setProfileId(UUID profileId) {
		this.profileId = profileId;
	}

	/**
	 * Obtiene el nombre del perfil.
	 * 
	 * @return nombre del perfil
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del perfil.
	 * 
	 * @param name nuevo nombre del perfil
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
}
