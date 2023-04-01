package commandManagers.commands;

import commandManagers.Command;

/**
 * The Exit class is a command that terminates the program.
 * It extends the Command class and overrides its execute() and checkArgument() methods.
 */
public class Exit extends Command {

    public Exit() {
        super(false);
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "Terminates the application (without saving collection).";
    }

    /**
     * Terminates the program by setting the isWorking variable in UserManager class to false.
     * Prints a message to the console to indicate that the program is terminating.
     * Overrides execute() method of the Command class.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.out.println("Closing program...");
            System.exit(0);
        }
    }

    /**
     * Checks if the argument of the Exit command is null.
     * If the argument is not null, prints a message to the console indicating that the Exit command does not take any arguments.
     * Overrides the checkArgument() method of the Command class.
     * @param inputArgument the argument of the Exit command
     * @return true if the inputArgument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Exit has no arguments!");
            return false;
        }
    }

}
