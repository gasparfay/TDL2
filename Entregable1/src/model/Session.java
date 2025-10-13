package model;

/**
 * Representa una sesión de usuario en la plataforma de streaming.
 * Contiene la cuenta y el perfil asociados a la sesión actual.
 * 
 * @author Grupo 8
 * @version 1.0
 * @see Account
 * @see Profile
 */
public class Session {
    /**
     * Cuenta asociada a la sesión actual.
     */
    private Account currentAccount;

    /**
     * Perfil asociado a la sesión actual.
     */
    private Profile currentProfile;

    /**
     * Obtiene la cuenta asociada a la sesión actual.
     * 
     * @return la cuenta asociada a la sesión
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Establece la cuenta asociada a la sesión actual.
     * 
     * @param currentAccount la nueva cuenta asociada a la sesión
     */
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    /**
     * Obtiene el perfil asociado a la sesión actual.
     * 
     * @return el perfil asociado a la sesión
     */
    public Profile getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Establece el perfil asociado a la sesión actual.
     * 
     * @param currentProfile el nuevo perfil asociado a la sesión
     */
    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
}
