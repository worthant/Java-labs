package serverLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketException;

public class DatagramServerConnectionFactory implements ServerConnectionFactory {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    @Override
    public ServerConnection initializeServer(int port) {
        try {
            return new DatagramServerConnection(port);
        } catch (SocketException e) {
            logger.fatal("Cannot initialize server connection!", e);
            System.exit(-1);
        }
        return null;
    }
}
