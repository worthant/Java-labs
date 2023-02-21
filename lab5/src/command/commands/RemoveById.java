package command.commands;
import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class RemoveById extends Command {

    public RemoveById() {
        super(true);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            int id = Integer.parseInt((String) getArgument());
            TreeSet<City> cities = CollectionManager.getCityCollection();
            Object obj = new Object();
            boolean flag = false;
            for (City city : cities) {
                if (city.getId() == id) {
                    obj = city;
                    flag = true;
                    break;
                }
            }
            if (flag)
                cities.remove(obj);
            else
                System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
        }
    }



    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда remove_by_id имеет аргумент типа данных int!");
            return false;
        } else if (inputArgument instanceof String) {
            try {
                Integer.parseInt((String) inputArgument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Команда remove_by_id имеет аргумент типа данных int!");
                return false;
            }
        }
        return false;
    }

}
