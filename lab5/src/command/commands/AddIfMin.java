package command.commands;

import command.Command;

import collection.City.City;
import collectionManagers.CollectionManager;

import java.util.TreeSet;

public class AddIfMin extends Command {

    public AddIfMin() {
        super(false);
    }

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
