package commandManagers.commands;

import collectionManagers.CityManager;
import commandManagers.Command;

/**
 * Class Info provides information about the collection:
 * the type of the collection, the date of its initialization, and the number of elements it contains.
 * Extends the Command class.
 */
public class Info extends Command {

    public Info() {
        super(false);
    }

    /**
     * Overrides the method execute() in the Command class.
     * Prints the type of the collection, the date of its initialization, and the number of elements it contains.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.out.println("Тип коллекции: " + CityManager.getCollection().getClass().toString());
            System.out.println("Дата инициализации: " + CityManager.getCollection().first().getCreationDate().toString());
            System.out.println("Количество элементов: " + CityManager.getCollection().toArray().length);
        }
    }

    /**
     * Checks if the argument of the Info command is null.
     * If the argument is not null, prints a message to the console indicating that the Info command does not take any arguments.
     * Overrides the checkArgument() method of the Command class.
     * @param inputArgument the argument of the Info command
     * @return true if the inputArgument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда info не имеет аргументов!");
            return false;
        }
    }

}
