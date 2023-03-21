package commandManagers;

import exceptions.CommandInterruptedException;

import java.io.InputStream;
import java.util.Scanner;

public class CommandExecutor {
    /**
     * Start executing commands from InputStream.
     *
     * @param input commands stream (File, System.in, e.t.c.)
     * @param mode  variant of command behavior (see CommandMode enum)
     */
    public void startExecuting(InputStream input, CommandMode mode) {
        Scanner cmdScanner = new Scanner(input);
        CommandManager commandManager = new CommandManager(mode, cmdScanner);
        while (cmdScanner.hasNext()) {
            String line = cmdScanner.nextLine().trim();
            if (line.isEmpty()) continue;
            try {
                commandManager.executeCommand(line.split(" "));
            } catch (CommandInterruptedException ex) {
                if (mode.equals(CommandMode.CLI_UserMode))
                    System.err.println("Выполнение команды было прервано. Вы можете продолжать работу. Программа возвращена в безопасное состояние.");
                else
                    System.err.println("Команда была пропущена... Обработчик продолжает работу");
            }
        }
    }
}
