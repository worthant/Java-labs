package command.commands;

import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.TreeSet;

public class SumOfMetersAboveSeaLevel extends Command {

    public SumOfMetersAboveSeaLevel() {
        super(false);
    }

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
