package command.commands;

import collection.City.City;
import collectionManagers.CollectionManager;
import collectionManagers.IdChecker;
import command.Command;

import java.util.TreeSet;

/**
 * Command to update a city by its id number.
 */
public class UpdateId extends Command {
    public UpdateId() {
        super(true);
    }

    /**
     * Executes the UpdateId command by updating a city with a specified id number in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            Object obj = IdChecker.checkCityById((String) getArgument());
            long id = IdChecker.getId();
            if (obj != null) {
                CollectionManager.getCityCollection().remove(obj);
                City city = CollectionManager.getNewCity();
                city.setId(id);
                CollectionManager.getCityCollection().add(city);
            } else
                System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
        }
    }


    /**
     * Checks if the input argument is valid for the UpdateId command.
     * The input argument should be a non-null string that can be parsed to an integer.
     * @param inputArgument the input argument to be checked
     * @return true if the input argument is valid, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда update_id имеет аргумент типа данных int!");
            return false;
        } else if (inputArgument instanceof String) {
            try {
                Integer.parseInt((String) inputArgument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Команда update_id имеет аргумент типа данных int!");
                return false;
            }
        }
        return false;
    }
}
