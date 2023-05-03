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
public class CommandManager {

    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");
    final LinkedHashMap<String, BaseCommand> commands;

    /**
     * Setup command manager and all of its commands.
     */
    public CommandManager() {
        commands = new LinkedHashMap<>();

        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("update", new UpdateCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("history", new HistoryCommand());
        commands.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevelCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevelCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("add", new AddCommand());
        commands.put("add_if_min", new AddIfMinCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());

    }

    /**
     * Get all commands from manager.
     *
     * @return Map of loaded commands
     */
    public LinkedHashMap<String, BaseCommand> getCommands() {
        return commands;
    }

    /**
     * Universe method for command executing.
     *
     * @param command request
     */
    public void executeCommand(CommandClientRequest command, CallerBack requester, ServerConnection answerConnection) {
        CommandStatusResponse response;
        try {
            BaseCommand baseCommand = Optional.ofNullable(commands.get(command.getCommandDescription().getName())).orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена"));
            HistoryCommand.addToCommandsHistoryQueue(baseCommand.getName());
            baseCommand.execute(command.getLineArgs());
            response = baseCommand.getResponse();
        } catch (IllegalArgumentException | NullPointerException e) {
            response = new CommandStatusResponse("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")", -90);
            logger.fatal(response.getResponse(), e);
        } catch (IndexOutOfBoundsException e) {
            response = new CommandStatusResponse("В команде предоставлено неправильное количество аргументов. Возможно, вам нужно обновить клиент", -91);
            logger.fatal(response.getResponse(), e);
        } catch (Exception e) {
            response = new CommandStatusResponse("В командном менеджере произошла непредвиденная ошибка!", -92);
            logger.fatal(response.getResponse(), e);
        }
        CommandResponseSender.sendResponse(response, answerConnection, requester);
    }

    public BaseCommand fromDescription(CommandDescription description) {
        return commands.get(description.getName());
    }
}
