package command.commands;

import collectionManagers.CollectionManager;
import command.Command;

import java.util.HashMap;
import java.util.Stack;

public class History extends Command {
    private static Stack<String> commandHistory;
    public History() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            History.getHistory();
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда save не имеет аргументов!");
            return false;
        }
    }
    public static void getHistory() {
        for (String string : commandHistory) {
            System.out.println(string);
        }
    }
    public static void initializeHistoryStack() {
        Stack<String> commandStack = new Stack<>();
        commandStack.setSize(14);
        History.commandHistory = commandStack;
    }

    public static void pushToHistoryStack(String command, String argument) {
        commandHistory.push(command + argument);
    }

}
