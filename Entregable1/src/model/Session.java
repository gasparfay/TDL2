package model;

/**
 * Representa una sesión de usuario en la plataforma de streaming.
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
     * @return cuenta asociada
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Establece la cuenta asociada a la sesión actual.
     * 
     * @param currentAccount nueva cuenta asociada
     */
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    /**
     * Obtiene el perfil asociado a la sesión actual.
     * 
     * @return perfil asociado
     */
    public Profile getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Establece el perfil asociado a la sesión actual.
     * 
     * @param currentProfile nuevo perfil asociado
     */
    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
}
