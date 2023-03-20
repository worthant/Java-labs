package commandManagers.commands;
import collection.City.City;
import collectionManagers.CityIdChecker;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import collectionManagers.IdManager;
import commandManagers.Command;

import java.util.TreeSet;

/**
 * Class RemoveById provides removal of an element from the collection by its ID number.
 * Extends the Command class.
 */
public class RemoveById extends Command {

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by id.";
    }

    /**
     * Overrides the method execute() in the Command class.
     * Removes an element from the collection by its ID number.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<City>, City> collectionHandler = CityManager.getInstance();
            Object obj = IdManager.checkCityById((String) this.getArgument());
            if (obj != null)
                collectionHandler.getCollection().remove(obj);
            else System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
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
