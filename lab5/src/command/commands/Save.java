package command.commands;


import collectionManagers.CollectionManager;
import command.Command;

/**
 * Save class represents the Save command in the program. When executed, it writes the current
 * collection to the file.
 */
public class Save extends Command {

    public Save() {
        super(false);
    }

    /**
     * Executes the Save command. Writes the current collection to the file.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager collectionManager = new CollectionManager();
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
