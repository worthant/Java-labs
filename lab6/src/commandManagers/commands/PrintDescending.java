package commandManagers.commands;
import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import commandManagers.Command;

import java.util.TreeSet;

/**
 The PrintDescending class is a command class that prints the population of all cities
 in descending order. It extends the abstract class Command.
 */
public class PrintDescending extends Command {
    public PrintDescending() {
        super(false);
    }

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String getDescr() {
        return "Prints the elements of the collection in descending order.";
    }

    /**
     Executes the PrintDescending command by first checking if it has an argument,
     then retrieving the collection of cities and creating an ArrayList of cities.
     The ArrayList is then reversed and the population of each city is printed in
     descending order.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }
            for (City city : manager.getCollection().descendingSet()) {
                System.out.println(city);
            }
        }
    }

    /**
     Checks whether the PrintDescending command has an argument. Since this command
     does not require an argument, it will return true if inputArgument is null.
     @param inputArgument the argument to be checked
     @return true if the argument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Print_descending has no arguments!");
            return false;
        }
    }
}
