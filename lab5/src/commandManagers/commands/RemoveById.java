package commandManagers.commands;
import collection.City.City;
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

    /**
     * Constructs a new command with the specified argument requirement.
     */
    public RemoveById() {
        super(true);
    }

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
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();

            Long finalId = IdManager.validateUserInput((String) this.getArgument());
            if (finalId == null) return;

            City city = IdManager.checkCityById(finalId);
            if (city != null)
                manager.getCollection().remove(city);
            else System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
        }
    }




    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда remove_by_id имеет аргумент (типа данных int!)");
            return false;
        }
        return false;
    }

}
