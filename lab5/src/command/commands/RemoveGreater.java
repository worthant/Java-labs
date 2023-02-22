package command.commands;
import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.TreeSet;

/**
 * This class represents the RemoveGreater command in the program. When executed, it removes all
 * elements from the collection that have a population greater than the specified city.
 */
public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(false);
    }

    /**
     * Executes the RemoveGreater command. Removes all elements from the collection that have a
     * population greater than the specified city.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            City newCity = CollectionManager.getNewCity();
            TreeSet<City> cities = CollectionManager.getCityCollection();
            TreeSet<City> cities2 = new TreeSet<>();

            for (City city: cities) {
                if (newCity.getPopulation() < city.getPopulation()) {
                    cities2.add(city);
                }
            }

            for(City city2 : cities2) {
                cities.remove(city2);
            }
        }
    }


    /**
     * Checks whether the given argument is valid for this command. Since this command doesn't
     * require an argument, this method always returns true.
     *
     * @param inputArgument the argument to be checked
     * @return true since this command doesn't require an argument
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда remove_greater не имеет аргументов!");
            return false;
        }
    }

}
