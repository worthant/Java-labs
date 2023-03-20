package commandManagers.commands;

import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import commandManagers.Command;

import java.util.TreeSet;

/**
 * Class Info provides information about the collection:
 * the type of the collection, the date of its initialization, and the number of elements it contains.
 * Extends the Command class.
 */
public class Info extends Command {

    public Info() {
        super(false);
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescr() {
        return "Prints information about the collection (type, initialization date, number of elements, etc.) to standard output.";
    }

    /**
     * Overrides the method execute() in the Command class.
     * Prints the type of the collection, the date of its initialization, and the number of elements it contains.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();

            System.out.println("Collection type: " + manager.getCollection().getClass().toString());
            System.out.println("Init date: " + manager.getCollection().first().getCreationDate().toString());
            System.out.println("Collection size: " + manager.getCollection().size());
        }
    }

    /**
     * Checks if the argument of the Info command is null.
     * If the argument is not null, prints a message to the console indicating that the Info command does not take any arguments.
     * Overrides the checkArgument() method of the Command class.
     * @param inputArgument the argument of the Info command
     * @return true if the inputArgument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда info не имеет аргументов!");
            return false;
        }
    }

}
