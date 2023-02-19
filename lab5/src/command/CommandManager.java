package command;

import collection.City.*;

import java.util.HashMap;

public class CommandManager {

    private static City cities;
    private final static HashMap<String, Command> commandMap;

    static {
        commandMap = new HashMap<>();

//        commandMap.put("help", new Help());
//        commandMap.put("info", new Info());
//        commandMap.put("show", new Show());
//        commandMap.put("add", new Add());
//        commandMap.put("remove_by_id", new RemoveById());
//        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
//        commandMap.put("exit", new Exit());
//        commandMap.put("remove_at", new RemoveAtIndex());
//        commandMap.put("add_if_max", new AddIfMax());
//        commandMap.put("remove_greater", new RemoveGreater());
//        commandMap.put("remove_any_by_type", new RemoveAnyByType());
//        commandMap.put("sum_of_age", new SumOfAge());
//        commandMap.put("print_field_ascending_color", new PrintFieldAscendingColor());
//        commandMap.put("execute_script", new ExecuteScript());
    }

    public static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
