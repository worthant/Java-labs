package commandManagers.commands;

import commandManagers.Command;
import commandManagers.CommandExecutor;
import commandManagers.CommandManager;
import commandManagers.CommandMode;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 ExecuteScript is a class that represents the execute_script command.
 It reads and executes a script from a file that contains a sequence of commands and arguments.
 It also prevents recursion by keeping track of file paths that have already been executed.
 */
public class ExecuteScript extends Command {

    /**
     Constructs a new ExecuteScript object.
     Initializes commandMap with the command map from CommandManager, and creates an empty ArrayList of filePaths.
     */
    public ExecuteScript() {
        super(true);
    }
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescr() {
        return "Reads and executes script from file.";
    }

    /**
     Executes the command by reading and parsing the script file.
     Each command in the file is executed using its corresponding command object from the command map.
     The method also checks for recursion by ensuring that a file path is not executed twice.
     */
    @Override
    public void execute() throws IllegalArgumentException {
        try {
            CommandExecutor executor = new CommandExecutor();
            if (checkRecursion(Path.of((String) this.getArgument()), new ArrayDeque<>())) {
                System.out.println("При анализе скрипта обнаружена рекурсия. Устраните ее перед исполнением.");
                return;
            }
            System.out.println("Executing script");
            executor.startExecuting(new FileInputStream(Path.of((String) this.getArgument()).toFile()), CommandMode.NonUserMode);
        } catch (InvalidPathException e) {
            System.out.println("Provided argument path isn't legal. Try again.");
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("File not found! Try again.");
        } catch (SecurityException e) {
            System.out.println("Access denied. Try again with another file.");
        } catch (IOException e) {
            System.out.println("Something went wrong during file handling. Try again. (" + e.getMessage() + ")");
        }
    }

    private boolean checkRecursion(Path path, ArrayDeque<Path> stack) throws IOException {
        if (stack.contains(path)) return true;
        stack.addLast(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find())
        {
            Path newPath = Path.of(patternMatcher.group().split(" ")[1]);
            if(checkRecursion(newPath, stack)) return true;
        }
        stack.removeLast();
        return false;
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда execute_script имеет аргумент - ссылку на файл!");
            return false;
        } else if (inputArgument instanceof String) {
            return true;
        }
        return false;
    }


}