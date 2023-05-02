package commandManager.commands;

import commandManager.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Shows reference about available commands.
 *
 * @author worthant
 * @since 1.0
 */
public class HelpCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.help");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescr() {
        return "Shows reference about available commands.";
    }

    /**
     * Prints information about the available commands in the program to the console.
     * Overrides execute() method of the Command class.
     */
    @Override
    public void execute(String[] args) {
        CommandManager manager = new CommandManager();

        String output = args.length == 1
                ? manager.getCommands().entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue().getArgs() + " : " + entry.getValue().getDescr())
                .collect(Collectors.joining("\n"))
                : Arrays.stream(Arrays.copyOfRange(args, 1, args.length))
                .map(arg -> arg + " : " + Optional.ofNullable(manager.getCommands().get(arg))
                        .map(BaseCommand::getDescr)
                        .orElse("This command is not found in manager"))
                .collect(Collectors.joining("\n"));

        response = CommandStatusResponse.ofString(output);
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
