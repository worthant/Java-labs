package commandManagers.commands;

import collection.City.City;
import collection.comparators.CityComparatorByMetersAboveSeaLevel;
import collectionManagers.CityManager;
import commandManagers.Command;

import java.util.TreeSet;

/**
 * Class PrintFieldDescendingMetersAboveSeaLevel provides output of the meters above sea level in descending order.
 * Extends the Command class.
 */
public class PrintFieldDescendingMetersAboveSeaLevel extends Command {

    public PrintFieldDescendingMetersAboveSeaLevel() {
        super(false);
    }

    /**
     * Overrides the method execute() in the Command class.
     * Prints the meters above sea level in descending order.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            TreeSet<City> cities2 = new TreeSet<>(new CityComparatorByMetersAboveSeaLevel());
            TreeSet<City> cities = CityManager.getCollection();
            for (City city: cities) {
                cities2.add(city);
            }

            for (City city2: cities2) {
                System.out.println(city2.getMetersAboveSeaLevel());
            }
        }
    }


    /**
     * Overrides the method checkArgument(Object inputArgument) in the Command class.
     * Checks if the argument is null.
     * @param inputArgument the argument to be checked.
     * @return true if the argument is null, false if not.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда print_field_ascending_color не имеет аргументов!");
            return false;
        }
    }

}
