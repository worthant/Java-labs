package serverLogic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class UdpServerConnectionFactory implements ServerConnectionFactory {
    public ServerConnection openConnection(InetAddress host, int port) throws IOException {
        DatagramChannel dc;
        SocketAddress addr;
        addr = new InetSocketAddress(host, port);
        dc = DatagramChannel.open();
        return new UdpServerConnection(dc, addr);
    }
}
