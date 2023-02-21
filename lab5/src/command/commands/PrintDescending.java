package command.commands;
import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class PrintDescending extends Command {
    public PrintDescending() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            TreeSet<City> cities = CollectionManager.getCityCollection();
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
