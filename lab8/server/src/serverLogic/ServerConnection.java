package serverLogic;

import requestLogic.StatusRequest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public interface ServerConnection {
    StatusRequest listenAndGetData() throws SocketTimeoutException, IOException;

    void sendData(byte[] data, InetAddress addr, int port);
}
