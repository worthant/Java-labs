package commandManager;

import client.Client;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.ReceiverManager;
import commandLogic.commandReceiverLogic.enums.ReceiverType;
import commandLogic.commandReceiverLogic.handlers.ArgumentReceiverHandler;
import commandLogic.commandReceiverLogic.handlers.NonArgReceiversHandler;
import commandManager.externalRecievers.*;
import exceptions.*;
import models.City;
import models.handlers.ModeManager;
import models.handlers.guiMode.GUIManager;
import models.handlers.nonUserMode.CityNonUserManager;
import models.handlers.userMode.CityCLIManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static commandManager.CommandMode.CLI_UserMode;

public class SingleCommandExecutor {

    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab8");
    private final ArrayList<CommandDescription> commands;
    private final ReceiverManager manager;
    private final CommandMode mode;
    private final Scanner scannerInput;

    public SingleCommandExecutor(ArrayList<CommandDescription> commands, InputStream input, CommandMode mode) throws CommandsNotLoadedException {
        if (commands == null || commands.isEmpty()) throw new CommandsNotLoadedException();

        this.commands = commands;
        this.mode = mode;
        this.manager = new ReceiverManager();
        this.scannerInput = new Scanner(input);

        setupManager();
    }

    private void setupManager() {
        manager.registerHandler(ReceiverType.NoArgs, new NonArgReceiversHandler());
        manager.registerHandler(ReceiverType.ArgumentRoute, new ArgumentReceiverHandler<>(City.class));

        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExecuteScriptReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExitReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentShowReceiver());

        ModeManager<City> modeManager = null;
        switch (mode) {
            case GUIMode -> modeManager = new GUIManager();
            case NonUserMode -> modeManager = new CityNonUserManager(scannerInput);
        }
        manager.registerReceiver(ReceiverType.ArgumentRoute, new ArgumentCityCommandReceiver(modeManager));
    }

    public synchronized void executeCommand(String line) {
        try {
            Client client = Client.getInstance();
            String[] lineArgs = line.split(" ");
            CommandDescription description = findCommandByName(lineArgs[0]);

            description.getReceiver().callReceivers(client.getName(), client.getPasswd(), manager, description, lineArgs);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error("Execution skipped due to wrong provided arguments! {}", e.getMessage());
        } catch (BuildObjectException | UnknownCommandException e) {
            logger.error(e.getMessage());
        } catch (WrongAmountOfArgumentsException e) {
            logger.error("Wrong amount of arguments! {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred in CommandManager! {}", e.getMessage());
        }
    }

    /**
     * Start executing commands from InputStream.
     */
    public synchronized void startExecutingScript() {
        while (scannerInput.hasNext()) {
            String line = scannerInput.nextLine();
            if (line.isEmpty()) continue;
            try {
                try {
                    Client client = Client.getInstance();
                    String[] lineArgs = line.split(" ");
                    CommandDescription description = Optional.ofNullable(commands).orElseThrow(CommandsNotLoadedException::new).stream().filter(x -> x.getName().equals(lineArgs[0])).findAny().orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена"));
                    description.getReceiver().callReceivers(client.getName(), client.getPasswd(), manager, description, lineArgs);
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

    private CommandDescription findCommandByName(String name) throws UnknownCommandException, CommandsNotLoadedException {
        return commands.stream()
                .filter(x -> x.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new UnknownCommandException("Specified command was not found"));
    }
}

