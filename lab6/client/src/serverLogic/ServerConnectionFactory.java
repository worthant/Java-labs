package serverLogic;

import java.io.IOException;
import java.net.InetAddress;

public interface ServerConnectionFactory {
    ServerConnection openConnection(InetAddress host, int port) throws IOException;
}
