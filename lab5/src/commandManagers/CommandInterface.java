package commandManagers;

import exceptions.BuildObjectException;

/**
 An interface that represents a command object that can be executed and checked for valid arguments.
 */
public interface CommandInterface {

    /**
     Executes the command.
     */
    void execute() throws BuildObjectException;

    /**
     * Base method for show command name
     *
     * @return command name
     */
    String getName();

    /**
     * Base method for show command description.
     *
     * @return command description
     */
    String getDescr();

    /**
     Checks if the provided argument is valid for the command.
     @param argument the argument to check for validity
     @return true if the argument is valid, false otherwise
     */
    boolean checkArgument(Object argument);
}
