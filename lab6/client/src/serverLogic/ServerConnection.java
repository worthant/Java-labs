package serverLogic;

import java.io.IOException;
import java.io.InputStream;

/**
 * Provides abstract server connection. Use factory methods to create the connection.
 *
 * @author boris
 * @see ServerConnectionFactory
 * @since 2.0
 */
public interface ServerConnection {
    /**
     * Method for open a connection.
     *
     * @throws IOException if I/O occurs
     */
    void openConnection() throws IOException;

    /**
     * Method for close a connection
     *
     * @throws IOException if I/O occurs
     */
    void closeConnection() throws IOException;

    /**
     * Method for send data to a server
     *
     * @param bytesToSend bytes to send
     * @return Response from a server
     * @throws IOException if I/O occurs
     */
    InputStream sendData(byte[] bytesToSend) throws IOException;
}
