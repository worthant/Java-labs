package command.commands;

import collection.City.City;
import collection.comparators.CityComparatorByMetersAboveSeaLevel;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.Comparator;
import java.util.TreeSet;

public class PrintFieldDescendingMetersAboveSeaLevel extends Command {

    public PrintFieldDescendingMetersAboveSeaLevel() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            TreeSet<City> cities2 = new TreeSet<>(new CityComparatorByMetersAboveSeaLevel());
            TreeSet<City> cities = CollectionManager.getCityCollection();
            for (City city: cities) {
                cities2.add(city);
            }

            for (City city2: cities2) {
                System.out.println(city2.getMetersAboveSeaLevel());
            }
        }
    }

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
