package command.commands;

import command.Command;
import command.CommandManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ExecuteScript extends Command {

    private HashMap<String, Command> commandMap;

    private ArrayList<String> scriptFiles = new ArrayList<>();


    public ExecuteScript() {
        super(true);
        this.commandMap = CommandManager.getCommandMap();
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            ArrayList<String> operationList = new ArrayList<>();

            try (InputStreamReader reader = new InputStreamReader(new FileInputStream((String) getArgument()))) {

                String command = "";
                int c;
                while (true) {

                    c = reader.read();

                    if (c != 10 && c != -1)
                        command += (Character.valueOf((char) c)).toString();
                    else if (c == 10) {
                        operationList.add(command);
                        command = "";
                    } else
                        break;
                }
                operationList.add(command);
            } catch (
                    FileNotFoundException e) {
                System.out.println("Файла по указаному пути не существует!");
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            for (String operation : operationList) {
                while (operation.contains("  "))
                    operation = operation.replaceAll("  ", " ");

                String[] input = operation.split(" ");

                if (input.length > 2) {
                    System.out.println("Требуется ввести *команда* *аргумент* (при его наличии)!");
                    return;
                }

                if (input[0].equals("execute_script")) {
                    if (input.length == 2 && checkRecursion((String) getArgument(), (String) getArgument(), scriptFiles)) {
                        commandMap.get("execute_script").execute();
                    }
                } else if (commandMap.containsKey(input[0])) {
                    commandMap.get(input[0]).execute();
                } else
                    System.out.println("Команды " + operation + " не существует! " + "Для уточнения команд воспользуйтесь командой help!");
            }
        }
    }

    private boolean checkRecursion(String filePath, String inputFilePath, ArrayList<String> scriptFiles) {
        if (filePath.equals(inputFilePath) || scriptFiles.contains(inputFilePath)) {
            System.out.println("Команда execute_script не выполянется, чтобы не допустить рекурсии!");
            return false;
        } else {
            scriptFiles.add(inputFilePath);
            return true;
        }
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
