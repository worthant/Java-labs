package commandManagers;

import collection.City.*;
import collectionManagers.CityManager;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.modeManagers.nonUserMode.CityNonUserManager;
import collectionManagers.modeManagers.userMode.CityCLIManager;
import commandManagers.commands.*;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;

/**
 The CommandManager class is responsible for managing all available commands in the application.
 It provides a static method getCommandMap() that returns a HashMap of all commands where the key is
 the command name and the value is an instance of the corresponding Command subclass.
 */
public class CommandManager {

    /**
     A HashMap object that stores all available commands in the application. The key is the command name
     and the value is an instance of the corresponding Command subclass.
     */
    private LinkedHashMap<String, Command> commandMap;

    /**
     * Default command manager setup.
     *
     * @since 1.0
     */
    public CommandManager(){
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("history", new History());
        commandMap.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevel());
        commandMap.put("print_descending", new PrintDescending());
        commandMap.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevel());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("add", new Add());
        commandMap.put("add_if_min", new AddIfMin());
        commandMap.put("remove_greater", new RemoveGreater());
    };

    /**
     * Constructor provides choice of commands behavior: ex. userMode or nonUserMode
     *
     * @since 2.0
     * @see CommandMode
     * @param mode Mode for CommandHandler
     * @param scanner Commands scanner
     */
    public CommandManager(CommandMode mode, Scanner scanner) {
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("history", new History());
        commandMap.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevel());
        commandMap.put("print_descending", new PrintDescending());
        commandMap.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevel());
        commandMap.put("remove_by_id", new RemoveById());

        ModeManager<City> handler = null;
        switch (mode)
        {
            case CLI_UserMode -> handler = new CityCLIManager();
            case NonUserMode -> handler = new CityNonUserManager(scanner);
        }

        commandMap.put("add", new Add(handler));
        commandMap.put("add_if_min", new AddIfMin(handler));
        commandMap.put("remove_greater", new RemoveGreater(handler));
    }

    /**
     Returns the commandMap HashMap that stores all available commands in the application.
     @return the HashMap of all commands where the key is the command name and the value is an instance
     of the corresponding Command subclass.
     */
    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * Universe method for command executing.
     */
    public void executeCommand(String[] args) {
        try {
            if (args.length > 1)
                Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("Команды " + args[0] + "не обнаружено :( ")).setArgument(args[1]);
            Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("Команды " + args[0] + "не обнаружено :( ")).execute();
        } catch (IllegalArgumentException | NullPointerException e) {
//            System.err.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            e.printStackTrace();
            throw new CommandInterruptedException(e);
        } catch (BuildObjectException | UnknownCommandException e) {
            System.err.println(e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (Exception e) {
            // "В командном менеджере произошла непредвиденная ошибка! " +
            e.printStackTrace();
            throw new CommandInterruptedException(e);
        }
    }
}
