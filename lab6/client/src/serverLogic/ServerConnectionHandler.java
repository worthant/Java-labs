package serverLogic;

public class ServerConnectionHandler {

    private static ServerConnection currentConnection;

    public static void setServerConnection(ServerConnection connection) {
        currentConnection = connection;
    }

    public static ServerConnection getCurrentConnection() {
        return currentConnection;
    }
}
