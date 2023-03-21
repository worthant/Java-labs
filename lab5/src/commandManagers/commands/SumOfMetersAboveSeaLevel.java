package commandManagers.commands;

import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import commandManagers.Command;

import java.util.TreeSet;

/**
 * Command class for computing the sum of meters above sea level of all cities in the collection.
 */
public class SumOfMetersAboveSeaLevel extends Command {

    public SumOfMetersAboveSeaLevel() {
        super(false);
    }

    @Override
    public String getName() {
        return "sum_of_meters_above_sea_level";
    }

    @Override
    public String getDescr() {
        return "Prints the sum of the values of the metersAboveSeaLevel field for all elements of the collection.";
    }

    /**
     * Computes the sum of meters above sea level of all cities in the collection and prints it to the console.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            Double sum = 0.0;

            for (City city: manager.getCollection()) {
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
