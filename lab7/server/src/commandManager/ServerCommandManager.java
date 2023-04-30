package commandManager;

import commandLogic.CommandDescription;
import commandManager.commands.*;
import exceptions.UnknownCommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;
import requests.CommandClientRequest;
import responseLogic.responseSenders.CommandResponseSender;
import responses.CommandStatusResponse;
import serverLogic.ServerConnection;

import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Command Manager for interactive collection manage.
 *
 * @author worthant
 * @since 1.0
 */
public class ServerCommandManager {

    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");
    final LinkedHashMap<String, BaseCommand> serverCommands;

    /**
     * Setup command manager and all of its commands.
     */
    public ServerCommandManager() {
        serverCommands = new LinkedHashMap<>();

        serverCommands.put("save", new SaveCommand());
    }

    /**
     * Get all commands from manager.
     *
     * @return Map of loaded commands
     */
    public LinkedHashMap<String, BaseCommand> getServerCommands() {
        return serverCommands;
    }

    /**
     * Universe method for command executing.
     *
     * @param command request
     */
    public void executeCommand(String[] args) {
        try {
            BaseCommand baseCommand = Optional.ofNullable(serverCommands.get(args[0])).orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена"));
            baseCommand.execute(args);
            System.out.println(baseCommand.getResponse().getResponse());
        } catch (Exception e) {
            logger.fatal("Something went wrong!");
        }
    }

    public BaseCommand fromDescription(CommandDescription description) {
        return serverCommands.get(description.getName());
    }
}
