package commandManagers.commands;


import collectionManagers.CityManager;
import commandManagers.Command;

/**
 * Save class represents the Save command in the program. When executed, it writes the current
 * collection to the file.
 */
public class Save extends Command {

    public Save() {
        super(false);
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Saves collection to file.";
    }

    /**
     * Executes the Save command. Writes the current collection to the file.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CityManager collectionManager = new CityManager();
            collectionManager.writeCollection();
        }
    }


    /**
     * Checks whether the given argument is valid for this command. Since this command doesn't
     * require an argument, this method always returns true.
     * @param inputArgument the argument to be checked
     * @return true since this command doesn't require an argument
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда save не имеет аргументов!");
            return false;
        }
    }

}
