package command.commands;
import command.Command;
public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.out.println(
                    "help : вывести справку по доступным командам\n" +
                            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                            "add {element} : добавить новый элемент в коллекцию\n" +
                            "update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                            "remove_by_id id : удалить элемент из коллекции по его id\n" +
                            "clear : очистить коллекцию\n" +
                            "save : сохранить коллекцию в файл\n" +
                            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                            "exit : завершить программу (без сохранения в файл)\n" +
                            "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                            "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                            "history : вывести последние 14 команд (без их аргументов)\n" +
                            "sum_of_meters_above_sea_level : вывести сумму значений поля metersAboveSeaLevel для всех элементов коллекции\n" +
                            "print_descending : вывести элементы коллекции в порядке убывания\n" +
                            "print_field_descending_meters_above_sea_level : вывести значения поля metersAboveSeaLevel всех элементов в порядке убывания\n");
        }
    }

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
