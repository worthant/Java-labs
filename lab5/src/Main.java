import collectionManagers.CollectionManager;
import user.UserManager;
/***
 * The main class of the application which
 * starts the program and runs the user interface.
 * @author boris
 */

public class Main {
    /**
     * The environment key to the CSV file for storing the collection.
    */
    private static final String ENV_KEY = "lab5";

    /**
     The main method that loads the collection from the CSV file and starts the user interface.
     @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        CollectionManager loader = new CollectionManager();
        loader.loadCollection(ENV_KEY);


        while (UserManager.isWorking()) {
            UserManager.requestCommand();
        }
    }
}