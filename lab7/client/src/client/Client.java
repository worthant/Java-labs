package Client;

public class Client {
    private final String name;
    private final char[] passwd;
    private static Client instance;

    private Client(String name, char[] passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public static Client getInstance(String name, char[] passwd) {
        if (instance == null) {
            instance = new Client(name, passwd);
        }
        return instance;
    }
    public static Client getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public char[] getPasswd() {
        return passwd;
    }
}

