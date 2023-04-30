package commandManager;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.ReceiverManager;
import commandLogic.commandReceiverLogic.enums.ReceiverType;
import commandLogic.commandReceiverLogic.handlers.ArgumentReceiverHandler;
import commandLogic.commandReceiverLogic.handlers.NonArgReceiversHandler;
import commandManager.externalRecievers.ArgumentCityCommandReceiver;
import commandManager.externalRecievers.ExecuteScriptReceiver;
import commandManager.externalRecievers.ExitReceiver;
import commandManager.externalRecievers.NonArgumentReceiver;
import exceptions.*;
import models.City;
import models.handlers.ModeManager;
import models.handlers.nonUserMode.CityNonUserManager;
import models.handlers.userMode.CityCLIManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static commandManager.CommandMode.CLI_UserMode;

/**
 * Class for executing commands. Provides different inputs for command executing.
 */
public class CommandExecutor {
    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");

    private final ArrayList<CommandDescription> commands;
    private final Scanner scannerInput;
    private final CommandMode mode;
    private final ReceiverManager manager;

    /**
     * Constructor :/
     *
     * @param commands array of commands
     * @param input    commands stream (File, System.in, e.t.c.)
     * @param mode     variant of command behavior (see CommandMode enum)
     */
    public CommandExecutor(ArrayList<CommandDescription> commands, InputStream input, CommandMode mode) throws CommandsNotLoadedException {
        if (commands == null) throw new CommandsNotLoadedException();

        this.commands = commands;
        this.scannerInput = new Scanner(input);
        this.mode = mode;
        manager = new ReceiverManager();

        manager.registerHandler(ReceiverType.NoArgs, new NonArgReceiversHandler());
        manager.registerHandler(ReceiverType.ArgumentRoute, new ArgumentReceiverHandler<>(City.class));

        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExecuteScriptReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExitReceiver());

        ModeManager<City> modeManager = null;
        switch (mode) {
            case CLI_UserMode -> modeManager = new CityCLIManager();
            case NonUserMode -> modeManager = new CityNonUserManager(scannerInput);
        }
        manager.registerReceiver(ReceiverType.ArgumentRoute, new ArgumentCityCommandReceiver(modeManager));
    }

    /**
     * Start executing commands from InputStream.
     */
    public void startExecuting() {
        while (scannerInput.hasNext()) {
            String line = scannerInput.nextLine();
            if (line.isEmpty()) continue;
            try {
                try {
                    String[] lineArgs = line.split(" ");
                    CommandDescription description = Optional.ofNullable(commands).orElseThrow(CommandsNotLoadedException::new).stream().filter(x -> x.getName().equals(lineArgs[0])).findAny().orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена"));
                    description.getReceiver().callReceivers(manager, description, lineArgs);
                } catch (IllegalArgumentException | NullPointerException e) {
                    logger.warn("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
                    throw new CommandInterruptedException(e);
                } catch (BuildObjectException | UnknownCommandException e) {
                    logger.error(e.getMessage());
                    throw new CommandInterruptedException(e);
                } catch (WrongAmountOfArgumentsException e) {
                    logger.error("Wrong amount of arguments! " + e.getMessage());
                    throw new CommandInterruptedException(e);
                } catch (Exception e) {
                    logger.error("В командном менеджере произошла непредвиденная ошибка! " + e.getMessage());
                    throw new CommandInterruptedException(e);
                }
            } catch (CommandInterruptedException ex) {
                if (mode.equals(CLI_UserMode))
                    logger.info("Выполнение команды было прервано. Вы можете продолжать работу. Программа возвращена в безопасное состояние.");
                else
                    logger.info("Команда была пропущена... Обработчик продолжает работу");
            }
        }
    }
}