package command.commands;

import command.Command;

import collection.City.City;
import collectionManagers.CollectionManager;

import java.util.TreeSet;

/**
 * Command to add a new city to the collection if it has a smaller population than the smallest city in the collection.
 * @author boris
 */
public class AddIfMin extends Command {

    public AddIfMin() {
        super(false);
    }

    /**
     * Adds a new city to the collection if its population is less than the population of the smallest city in the collection.
     * Prints a message indicating that the command does not take arguments if an argument is provided.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            City newCity = CollectionManager.getNewCity();
            TreeSet<City> cities = CollectionManager.getCityCollection();

            if (newCity.getPopulation() < cities.first().getPopulation()) {
                cities.add(newCity);
            }

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
            System.out.println("Команда add_if_max не имеет аргументов!");
            return false;
        }
    }
}
