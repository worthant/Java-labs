package command.commands;
import collectionManagers.CityManager;
import command.Command;

/**
 * Command to add a new city to the collection.
 * @author boris
 */
public class Add extends Command{

    public Add() {
        super(false);
    }

    /**
     * Adds a new city to the collection if an argument is not provided.
     * Prints a message indicating that the command does not take arguments otherwise.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CityManager.getCityCollection().add(CityManager.getNewCity());
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
            System.out.println("Команда add не имеет аргументов!");
            return false;
        }
    }

}
