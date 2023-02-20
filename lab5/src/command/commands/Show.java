package command.commands;

import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.TreeSet;

public class Show extends Command {

    public Show() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            if (CollectionManager.getCityCollection().isEmpty()) {
                System.out.println("Текущая коллекция пуста!");
            } else {
                TreeSet<City> citySet = CollectionManager.getCityCollection();
                for (City city : citySet) {
                    System.out.println(city.toString());
                }
            }
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда show не имеет аргументов!");
            return false;
        }
    }

}
