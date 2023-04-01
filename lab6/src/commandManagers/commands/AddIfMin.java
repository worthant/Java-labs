package commandManagers.commands;

import collectionManagers.CollectionManager;
import collectionManagers.modeManagers.ModeManager;
import commandManagers.Command;

import collection.City.City;
import collectionManagers.CityManager;
import exceptions.BuildObjectException;

import java.util.TreeSet;

/**
 * Command to add a new city to the collection if it has a smaller population than the smallest city in the collection.
 *
 * @author boris
 */
public class AddIfMin extends Command {
    ModeManager<City> handler;

    /**
     * Default constructor from 1.0
     */
    public AddIfMin() {
        super(false);
    }

    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     * @since 2.0
     */
    public AddIfMin(ModeManager<City> handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescr() {
        return "Adds element if it's value lower than min value.";
    }

    /**
     * Adds a new city to the collection if its population is less than the population of the smallest city in the collection.
     * Prints a message indicating that the command does not take arguments if an argument is provided.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }
            City newCity = handler.buildObject();

            if (newCity.getPopulation() < manager.getCollection().first().getPopulation()) {
                manager.addElementToCollection(newCity);
                System.out.println("Element added!");
            } else {
                System.out.println("Element not added: it's not lower than min value.");
            }

        }
    }

    /**
     * Checks whether an argument is provided or not.
     *
     * @param inputArgument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Add_if_min has no arguments!");
            return false;
        }
    }
}
