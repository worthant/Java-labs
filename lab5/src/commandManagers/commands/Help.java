package commandManagers.commands;
import commandManagers.Command;
import commandManagers.CommandManager;

import java.util.HashMap;
import java.util.Optional;

/**
 * The Help class is a command that provides information about the available commands in the program.
 * It extends the Command class and overrides its execute() and checkArgument() methods.
 */
public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescr() {
        return "Shows reference about available commands.";
    }

    /**
     * Prints information about the available commands in the program to the console.
     * Overrides execute() method of the Command class.
     */
    @Override
    public void execute() {
        CommandManager manager = new CommandManager();

        if (this.getArgument() == null)
            manager.getCommandMap().forEach((name, command) -> System.out.println(name + " : " + command.getDescr()));
        else {
            var command = manager.getCommandMap().get(this.getArgument());
            System.out.println(this.getArgument() + " : " + Optional.ofNullable(command).map(Command::getDescr).orElse("This command is not found in manager"));
        }
    }


//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            System.out.println(
//                    "help : вывести справку по доступным командам\n" +
//                            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
//                            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
//                            "add {element} : добавить новый элемент в коллекцию\n" +
//                            "update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
//                            "remove_by_id id : удалить элемент из коллекции по его id\n" +
//                            "clear : очистить коллекцию\n" +
//                            "save : сохранить коллекцию в файл\n" +
//                            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
//                            "exit : завершить программу (без сохранения в файл)\n" +
//                            "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
//                            "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
//                            "history : вывести последние 14 команд (без их аргументов)\n" +
//                            "sum_of_meters_above_sea_level : вывести сумму значений поля metersAboveSeaLevel для всех элементов коллекции\n" +
//                            "print_descending : вывести элементы коллекции в порядке убывания\n" +
//                            "print_field_descending_meters_above_sea_level : вывести значения поля metersAboveSeaLevel всех элементов в порядке убывания\n");
//        }
//    }

    /**
     * Checks if the argument of the Exit command is null.
     * If the argument is not null, prints a message to the console indicating that the Exit command does not take any arguments.
     * Overrides the checkArgument() method of the Command class.
     * @param inputArgument the argument of the Exit command
     * @return true if the inputArgument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда help не имеет аргументов!");
            return false;
        }
    }

}
