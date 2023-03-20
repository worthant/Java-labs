package commandManagers.commands;

import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import collectionManagers.IdManager;
import collectionManagers.modeManagers.ModeManager;
import commandManagers.Command;
import exceptions.BuildObjectException;

import java.util.TreeSet;

/**
 * Command to update a city by its id number.
 */
public class UpdateId extends Command {

    ModeManager<City> modeManager;

    /**
     * Default constructor from 1.0
     */
    public UpdateId() {
        super(true);
    }

    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param modeManager ModuleHandler for operating
     */
    public UpdateId(ModeManager<City> modeManager) {
        super(true);
        this.modeManager = modeManager;
    }

    @Override
    public String getName() {
        return "update_id {element}";
    }

    @Override
    public String getDescr() {
        return "Updates the value of the collection element whose id is equal to the given.";
    }

    /**
     * Executes the UpdateId command by updating a city with a specified id number in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();

            Long finalId = IdManager.validateUserInput((String) this.getArgument());
            if (finalId == null) return;

            Object obj = IdManager.checkCityById(finalId);
            if (obj == null) {
                System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
                return;
            } else manager.getCollection().remove(obj);

            City newCity = modeManager.buildObject();
            newCity.setId(finalId);

            manager.addElementToCollection(newCity);

            System.out.println("Updated City object with id : " + finalId);
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
