package commandManagers.commands;

import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import commandManagers.Command;

import java.util.TreeSet;

/**
 * Command to clear the collection.
 * @author boris
 */
public class Clear extends Command {

    public Clear() {
        super(false);
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "Clears collection";
    }

    /**
     * Clears the collection if no argument is provided.
     * Prints a message indicating that the command does not take arguments otherwise.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("There is nothing to clear)");
                return;
            }
            manager.clearCollection();
            System.out.println("Collection successfully cleared!");
        }
    }

    /**
     * Checks whether an argument is provided or not.
     * @param inputArgument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Clear has no arguments!");
            return false;
        }
    }

}
