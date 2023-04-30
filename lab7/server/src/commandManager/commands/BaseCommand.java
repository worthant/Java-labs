package commandManager.commands;

import responses.CommandStatusResponse;

/**
 * Base interface for command implementation. You should implement it before applying command in CommandManager
 *
 * @author worthant
 * @see commandManager.CommandManager
 * @since 1.0
 */
public interface BaseCommand {
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
     * Base method for show command arguments
     *
     * @return command arguments pattern
     */
    default String getArgs() {
        return "";
    }

    /**
     * Base method for command executing.
     *
     * @param args full array of entered line.
     * @throws IllegalArgumentException when command can't understand given arguments
     */
    void execute(String[] args) throws IllegalArgumentException;

    /**
     * Base method for get command Output
     *
     * @return Full command output
     * @since 2.0
     */
    CommandStatusResponse getResponse();
}
