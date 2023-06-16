package clientLogic;

import collectionStorageManager.PostgreSQLManager;
import exceptions.UserNotAuthenticatedException;

/**
 * Class for managing clients
 */
public class ClientHandler {
    private final String name;
    private final char[] passwd;
    private static long userId;

    public ClientHandler(String name, char[] passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    /**
     * Register user in database
     * @return true, if registered
     */
    public boolean regUser() {
        PostgreSQLManager manager = new PostgreSQLManager();
        long id = manager.regUser(name, passwd);
        if (id > 0) {
            userId = id;
            return true;
        }
        return false;
    }

    /**
     * Authenticate user in database
     * @return true, if authenticated
     */
    public boolean authUser() {
        PostgreSQLManager manager = new PostgreSQLManager();
        long id = manager.authUser(name, passwd);
        if (id > 0) {
            userId = id;
            return true;
        }
        return false;
    }

    public void authUserCommand() throws UserNotAuthenticatedException {
        PostgreSQLManager manager = new PostgreSQLManager();
        long id = manager.authUser(name, passwd);
        if (id > 0) {
            userId = id;
        } else {
            throw new UserNotAuthenticatedException("Can't execute command: user is not authenticated");
        }
    }

    public static long getUserId() {
        return userId;
    }
}
