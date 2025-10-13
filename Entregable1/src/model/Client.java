package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una sesión en ejecución de la plataforma de streaming.
 * Mantiene la sesión del cliente (cuenta y perfil activos) y centraliza
 * el acceso a sus funcionalidades a través de esta instancia.
 * 
 * @author Grupo 8
 * @version 1.1
 * @see Account
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
        this.accounts = new ArrayList<Account> (); // Inicializa como una lista vacía
    }
}
