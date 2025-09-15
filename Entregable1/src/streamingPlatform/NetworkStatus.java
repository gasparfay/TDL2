package streamingPlatform;

/**
 * Representa el estado de la red para la plataforma de streaming.
 */
public class NetworkStatus {

    /**
     * Velocidad de la red en Mbps.
     */
    private double speed;

    /**
     * Latencia de la red en milisegundos.
     */
    private double latency;

    /**
     * Nivel de buffer actual en segundos.
     */
    private double bufferLevel;

    /**
     * Obtiene la velocidad de la red en Mbps.
     * 
     * @return velocidad de la red
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Obtiene la latencia de la red en milisegundos.
     * 
     * @return latencia de la red
     */
    public double getLatency() {
        return latency;
    }

    /**
     * Obtiene el nivel de buffer actual en segundos.
     * 
     * @return nivel de buffer
     */
    public double getBufferLevel() {
        return bufferLevel;
    }
}
