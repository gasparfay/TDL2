package streamingPlatform;

public class Client {
    /**
     * La sesión activa del cliente.
     * Relación: Un Client tiene una sesión activa.
     */
    private Session session;

    /**
     * Gestor de cuentas del cliente.
     * Relación: Un Client accede y gestiona las cuentas a través de AccountManager.
     */
    private AccountManager accountManager;

    /**
     * Gestor de las interacciones de los usuario con los contenidos.
     * Relación: Un Client registra, accede y gestiona las interacciones de los
     * usuarios con los
     * contenidos a través de InteractionContentManager.
     */
    // private InteractionContentManager interactionContentManager;

    /**
     * Gestor de estadísticas del cliente.
     * Relación: Un Client accede y gestiona estadísticas tanto globales como del
     * usuario mediante StatsManager.
     */
    // private StatsManager statsManager;

    /**
     * Gestor de reproducción de contenido.
     * Relación: Un Client controla la reproducción con PlaybackManager.
     */
    // private PlaybackManager playbackManager;

    /**
     * Gestor de acceso al contenido.
     * Relación: Un Client accede al contenido mediante ContentManager.
     */
    // private ContentManager contentManager;

    /**
     * Constructor de Client.
     * Instancia todos los gestores y la sesión del cliente.
     */
    /*
     * public Client() {
     * this.session = new Session();
     * this.accountManager = new AccountManager();
     * this.interactionContentManager = new InteractionContentManager();
     * this.statsManager = new StatsManager();
     * this.playbackManager = new PlaybackManager();
     * this.contentManager = new ContentManager();
     * }
     */

    /**
     * Obtiene la sesión activa del cliente.
     * 
     * @return la sesión actual
     */
    public Session getSession() {
        return session;
    }

}
