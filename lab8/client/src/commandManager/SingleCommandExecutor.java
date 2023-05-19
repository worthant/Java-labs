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

import java.util.ArrayList;
import java.util.Optional;

import static commandManager.CommandMode.CLI_UserMode;

public class SingleCommandExecutor {

    private final ArrayList<CommandDescription> commands;
    private final ReceiverManager manager;
    private final CommandMode mode;

    /**
     * Constructor :/
     *
     * @param commands array of commands
     */
    public SingleCommandExecutor(ArrayList<CommandDescription> commands, CommandMode mode) throws CommandsNotLoadedException {
        if (commands == null) throw new CommandsNotLoadedException();

        this.commands = commands;
        this.mode = mode;
        manager = new ReceiverManager();

        manager.registerHandler(ReceiverType.NoArgs, new NonArgReceiversHandler());
        manager.registerHandler(ReceiverType.ArgumentRoute, new ArgumentReceiverHandler<>(City.class));

        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExecuteScriptReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExitReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentShowReceiver());

        ModeManager<City> modeManager = new GUIManager();
        manager.registerReceiver(ReceiverType.ArgumentRoute, new ArgumentCityCommandReceiver(modeManager));
    }

    public void executeCommand(String line) {
        try {
            try {
                Client client = Client.getInstance();
                String[] lineArgs = line.split(" ");
                CommandDescription description = Optional.ofNullable(commands).orElseThrow(CommandsNotLoadedException::new).stream().filter(x -> x.getName().equals(lineArgs[0])).findAny().orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена"));
                description.getReceiver().callReceivers(client.getName(), client.getPasswd(), manager, description, lineArgs);
            } catch (IllegalArgumentException | NullPointerException e) {
                // "Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
                throw new CommandInterruptedException(e);
            } catch (BuildObjectException | UnknownCommandException e) {
                // e.getMessage();
                throw new CommandInterruptedException(e);
            } catch (WrongAmountOfArgumentsException e) {
                // "Wrong amount of arguments! " + e.getMessage());
                throw new CommandInterruptedException(e);
            } catch (Exception e) {
                // "В командном менеджере произошла непредвиденная ошибка! " + e.getMessage());
                throw new CommandInterruptedException(e);
            }
        } catch (CommandInterruptedException ex) {
            if (mode.equals(CLI_UserMode)) {
                // "Выполнение команды было прервано. Вы можете продолжать работу. Программа возвращена в безопасное состояние.");
            } else {
                // "Команда была пропущена... Обработчик продолжает работу");
            }
        }
    }
}
