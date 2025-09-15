package model;

import java.util.UUID;

/**
 * Representa una sesión de usuario en la plataforma de streaming.
 *
 * @author Grupo 8
 * @version 1.0
 * @see Account
 */
public class Session {
    /**
     * Identificador único de la sesión.
     */
    private UUID id;

    /**
     * Identificador de la cuenta asociada a la sesión.
     */
    private UUID accId;

    /**
     * Indica si el usuario tiene privilegios de administrador.
     */
    private boolean isAdmin;

    /**
     * Obtiene el identificador único de la sesión.
     * 
     * @return id de la sesión
     */
    public UUID getId() {
        return id;
    }

    /**
     * Establece el identificador único de la sesión.
     * 
     * @param id nuevo id de la sesión
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador de la cuenta asociada.
     * 
     * @return id de la cuenta
     */
    public UUID getAccId() {
        return accId;
    }

    /**
     * Establece el identificador de la cuenta asociada.
     * 
     * @param accId nuevo id de la cuenta
     */
    public void setAccId(UUID accId) {
        this.accId = accId;
    }

    /**
     * Obtiene si el usuario es administrador.
     * 
     * @return true si es administrador, false si no
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Establece si el usuario actual es administrador.
     * 
     * @param isAdmin true si es administrador, false si no
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
