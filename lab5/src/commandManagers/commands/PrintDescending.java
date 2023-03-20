package commandManagers.commands;
import collection.City.City;
import collectionManagers.CityManager;
import commandManagers.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 The PrintDescending class is a command class that prints the population of all cities
 in descending order. It extends the abstract class Command.
 */
public class PrintDescending extends Command {
    public PrintDescending() {
        super(false);
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
            TreeSet<City> cities = CityManager.getCollection();
            ArrayList<City> list = new ArrayList<>();
            for (City city : cities) {
                list.add(city);
            }
            Collections.reverse(list);
            for(City city : list) {
                System.out.println(city.getPopulation());
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
            System.out.println("Команда print_field_ascending_color не имеет аргументов!");
            return false;
        }
    }
}
