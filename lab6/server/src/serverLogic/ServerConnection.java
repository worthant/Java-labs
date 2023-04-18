package serverLogic;

import exceptions.NotAvailableException;
import requestLogic.StatusRequest;

import java.net.InetAddress;

public interface ServerConnection {
    StatusRequest listenAndGetData() throws NotAvailableException;

    void sendData(byte[] data, InetAddress addr, int port);
}
