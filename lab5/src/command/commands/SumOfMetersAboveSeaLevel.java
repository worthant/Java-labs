package command.commands;

import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.TreeSet;

/**
 * Command class for computing the sum of meters above sea level of all cities in the collection.
 */
public class SumOfMetersAboveSeaLevel extends Command {

    public SumOfMetersAboveSeaLevel() {
        super(false);
    }

    /**
     * Computes the sum of meters above sea level of all cities in the collection and prints it to the console.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            TreeSet<City> cities = CollectionManager.getCityCollection();
            Double sum = 0.0;

            for (City city: cities) {
                sum += city.getMetersAboveSeaLevel();
            }
            System.out.println(sum);
        }
    }

    /**
     * Checks if the input argument is null, and returns true if it is, or false and prints an error message otherwise.
     * @param inputArgument the argument to check
     * @return true if the input argument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда sum_of_age не имеет аргументов!");
            return false;
        }
    }

}
