package command;

import collection.City.*;
import command.commands.*;

import java.util.HashMap;

/**
 The CommandManager class is responsible for managing all available commands in the application.
 It provides a static method getCommandMap() that returns a HashMap of all commands where the key is
 the command name and the value is an instance of the corresponding Command subclass.
 */
public class CommandManager {

    /**
     A City object used for creating instances of classes that store data about cities.
     */
    private static City cities;

    /**
     A HashMap object that stores all available commands in the application. The key is the command name
     and the value is an instance of the corresponding Command subclass.
     */
    private final static HashMap<String, Command> commandMap;

    /**
     Initializes the commandMap HashMap and creates instances of all available commands.
     */
    static {
        commandMap = new HashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("add", new Add());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("add_if_min", new AddIfMin());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("history", new History());
        commandMap.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevel());
        commandMap.put("print_descending", new PrintDescending());
        commandMap.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevel());
    }
    /**
     Returns the commandMap HashMap that stores all available commands in the application.
     @return the HashMap of all commands where the key is the command name and the value is an instance
     of the corresponding Command subclass.
     */
    public static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
