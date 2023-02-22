package command.commands;

import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.TreeSet;

/**
 * Command to show all cities in the collection.
 */
public class Show extends Command {

    public Show() {
        super(false);
    }

    /**
     * Executes the Show command by printing all the cities in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            if (CollectionManager.getCityCollection().isEmpty()) {
                System.out.println("Текущая коллекция пуста!");
            } else {
                TreeSet<City> citySet = CollectionManager.getCityCollection();
                for (City city : citySet) {
                    System.out.println(city.toString());
                }
            }
        }
    }


    /**
     * Checks if the input argument is valid for the Show command.
     * The input argument should be null.
     *
     * @param inputArgument the input argument to be checked
     * @return true if the input argument is valid, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда show не имеет аргументов!");
            return false;
        }
    }

}
