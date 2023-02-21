package command.commands;
import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.TreeSet;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(false);
    }

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
