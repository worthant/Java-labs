package serverLogic;

public interface ServerConnectionFactory {
    ServerConnection initializeServer(int port);
}
