package command;

import collection.City.*;
import command.commands.*;

import java.util.HashMap;

public class CommandManager {

    private static City cities;
    private final static HashMap<String, Command> commandMap;

    static {
        commandMap = new HashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("add", new Add());
//        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("add_if_min", new AddIfMin());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("history", new History());
        commandMap.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevel());
//        commandMap.put("print_descending", new PrintDescending());
        commandMap.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevel());
    }

    public static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
