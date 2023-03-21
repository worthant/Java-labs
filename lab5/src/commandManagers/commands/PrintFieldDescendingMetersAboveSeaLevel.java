package commandManagers.commands;

import collection.City.City;
import collection.comparators.CityComparatorByMetersAboveSeaLevel;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
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

    @Override
    public String getName() {
        return "print_field_descending_meters_above_sea_level";
    }

    @Override
    public String getDescr() {
        return "Prints the field values metersAboveSeaLevel of all elements in descending order.";
    }

    /**
     * Overrides the method execute() in the Command class.
     * Prints the meters above sea level in descending order.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            for (City city : manager.getCollection().descendingSet()) {
                System.out.println(city.getMetersAboveSeaLevel());
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
            System.out.println("Команда print_field_descending_meters_above_sea_level не имеет аргументов!");
            return false;
        }
    }

}
