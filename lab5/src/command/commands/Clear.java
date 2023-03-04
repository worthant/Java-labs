package command.commands;

import collectionManagers.CityManager;
import command.Command;

/**
 * Command to clear the collection.
 * @author boris
 */
public class Clear extends Command {

    public Clear() {
        super(false);
    }

    /**
     * Clears the collection if no argument is provided.
     * Prints a message indicating that the command does not take arguments otherwise.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CityManager.getCityCollection().clear();
            System.out.println("Коллекция успешно очищена!");
        }
    }

    /**
     * Checks whether an argument is provided or not.
     * @param inputArgument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда clear не имеет аргументов!");
            return false;
        }
    }

}
