package client;

public class Client {
    private final String name;
    private final char[] passwd;
    private static volatile Client instance;

    private Client(String name, char[] passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public static Client getInstance(String name, char[] passwd) {
        if (instance == null) {
            synchronized (Client.class) {
                if (instance == null) {
                    instance = new Client(name, passwd);
                }
            }
        }
        return instance;
    }

    public static Client getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Client instance has not been initialized. Call getInstance(String, char[]) first.");
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public char[] getPasswd() {
        return passwd;
    }
}


