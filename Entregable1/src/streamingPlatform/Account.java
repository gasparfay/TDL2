package streamingPlatform;

import java.util.UUID;
import java.util.concurrent.Flow.Subscription;

/**
 * Representa una cuenta de usuario en la plataforma de streaming.
 *
 * @author Grupo 8
 * @version 1.0
 * @see Profile
 */
public class Account {
	/**
	 * Identificador único de la cuenta.
	 */
	private UUID accId;

	/**
	 * Correo electrónico del usuario.
	 */
	private String email;

	/**
	 * Contraseña de la cuenta.
	 */
	private String password;

	/**
	 * Tipo de suscripción de la cuenta. Es un valor del enum Subscription, que esta
	 * creado en el diagrama UML de clases.
	 */
	private Subscription subscriptionType;

	/**
	 * Cantidad de perfiles asociados a la cuenta. De 0 a 5.
	 */
	private int profileAmount;

	/**
	 * Vector de perfiles asociados a la cuenta (máximo 5).
	 */
	private Profile[] profiles = new Profile[5];

	/**
	 * Indica si la cuenta tiene privilegios de administrador.
	 */
	private boolean isAdmin;
	/**
	 * Reporte financiero asociado a la cuenta.
	 * Relación: Se asocia a la cuenta para que el cliente no cree
	 * instancias de FinancialReport cada vez que se cambie de usuario.
	 */
	// private FinancialReport financialReport;

	/**
	 * Obtiene el identificador único de la cuenta.
	 * 
	 * @return accId de la cuenta
	 */
	public UUID getAccId() {
		return accId;
	}

	/**
	 * Establece el identificador único de la cuenta.
	 * 
	 * @param accId nuevo accId de la cuenta
	 */
	public void setAccId(UUID accId) {
		this.accId = accId;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 * 
	 * @return email del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 * 
	 * @param email nuevo email del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la contraseña de la cuenta.
	 * 
	 * @return contraseña
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña de la cuenta.
	 * 
	 * @param password nueva contraseña
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene el tipo de suscripción de la cuenta.
	 * 
	 * @return tipo de suscripción
	 */
	public Subscription getSubscriptionType() {
		return subscriptionType;
	}

	/**
	 * Establece el tipo de suscripción de la cuenta.
	 * 
	 * @param subscriptionType nuevo tipo de suscripción.
	 */
	public void setSubscriptionType(Subscription subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	/**
	 * Obtiene la cantidad de perfiles asociados a la cuenta.
	 * 
	 * @return cantidad de perfiles
	 */
	public int getProfileAmount() {
		return profileAmount;
	}

	/**
	 * Obtiene el vector de perfiles asociados a la cuenta.
	 * 
	 * @return vector de perfiles (Profile[])
	 * @see Profile
	 */
	public Profile[] getProfiles() {
		return profiles;
	}
}
