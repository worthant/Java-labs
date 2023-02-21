package command.commands;
import collection.City.City;
import collectionManagers.CollectionManager;
import collectionManagers.IdChecker;
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
            Object obj = IdChecker.checkCityById((String) getArgument());
            if (obj != null)
                CollectionManager.getCityCollection().remove(obj);
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
