package command.commands;
import collectionManagers.CityIdChecker;
import collectionManagers.CityManager;
import collectionManagers.IdManager;
import command.Command;

/**
 * Class RemoveById provides removal of an element from the collection by its ID number.
 * Extends the Command class.
 */
public class RemoveById extends Command {

    public RemoveById() {
        super(true);
    }

    /**
     * Overrides the method execute() in the Command class.
     * Removes an element from the collection by its ID number.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            Object obj = IdManager.checkCityById((String) getArgument());
            if (obj != null)
                CityManager.getCityCollection().remove(obj);
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
