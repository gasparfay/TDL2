package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un cliente en la plataforma de streaming.
 * Mantiene la sesión activa del cliente y las cuentas asociadas.
 * 
 * @author Grupo 8
 * @version 1.0
 * @see Account
 * @see Session
 */
public class Client {
    /**
     * La sesión activa del cliente.
     */
    private Session session;

    /**
     * Lista de cuentas de la plataforma.
     */
    private List<Account> accounts;

    /**
     * Constructor de Client.
     * Inicializa los atributos necesarios.
     */
    public Client() {
        this.session = new Session();
        this.accounts = new ArrayList<>();
    }
}
