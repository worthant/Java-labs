import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import commandManagers.CommandExecutor;
import commandManagers.CommandMode;

import java.util.TreeSet;

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
        CityManager loader = new CityManager();
        loader.loadCollection(ENV_KEY);
        CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();

        // commands
        System.out.println("Welcome to CLI! Now you are operating with collection of \n  type: " + manager.getCollection().getClass().getName() + ", \n  filled with elements of type: " + manager.getFirstOrNew().getClass().getName());
        System.out.println("Now you can enter the commands. Use help for reference.");
        CommandExecutor executor = new CommandExecutor();
        executor.startExecuting(System.in, CommandMode.CLI_UserMode);
    }
}