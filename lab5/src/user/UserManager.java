package user;

import command.Command;
import command.CommandManager;
import collection.City.*;
import collectionManagers.*;
import command.commands.History;

import java.time.LocalDate;
import java.util.*;

/**
 The UserManager class manages the input of commands from the user and
 creation of new city by user input.
 This class initializes a command map from the CommandManager, scanner for
 user input and history queue of the commands from the History class.
 */
public class UserManager {

    /**
     * A hashmap that stores command objects with their respective command names.
     * This hashmap is initialized using the getCommandMap method from CommandManager.
     */
    private static HashMap<String, Command> commandMap;

    /**
     * Scanner object for reading user input from the console.
     */
    private static Scanner scanner;

    /**
     * A boolean that determines whether the user is still interacting with the program.
     * This is set to true by default and set to false if a NoSuchElementException is caught.
     */
    private static boolean isWorking;

    /**
     * Static block that initializes the commandMap, scanner and isWorking variable.
     * This block is run only once when the class is first loaded.
     * The commandMap is initialized using the getCommandMap method from CommandManager.
     * The scanner reads input from System.in. The isWorking variable is set to true.
     */
    static {
        History.initializeCommandsHistoryQueue();
        commandMap = CommandManager.getCommandMap();
        scanner = new Scanner(System.in);
        isWorking = true;
    }

    /**
     * The requestCommand method takes user input for a command and its arguments,
     * and executes the corresponding command using the CommandMap.
     * If the command is not recognized, the user is informed of the same.
     *
     * This method uses a try-catch block to handle NoSuchElementException, which is
     * thrown when the user presses Ctrl+D in the console.
     */
    public static void requestCommand() {

        try {
            System.out.print("Введите команду: ");
            String userManager = scanner.nextLine().trim();

            String[] input = userManager.split(" ");
            String command = input[0];
            String argument;

            // configure command argument
            if (input.length == 1)
                argument = null;
            else if (input.length == 2)
                argument = input[1];
            else {
                System.out.println("Некорректный формат ввода! \nВведите: {команда} {аргумент} (если есть!)");
                return;
            }

            // execute command
            if (commandMap.containsKey(command)) {
                commandMap.get(command).setArgument(argument);
                commandMap.get(command).execute();
                History.addToCommandsHistoryQueue(command);
            } else {
                System.out.println("Команды " + command + " не существует!" +
                        "\nЧтобы посмотреть список доступных команд напишите: help");
            }
        } catch(NoSuchElementException e) {
            System.out.println("зачем прогу ломаеш?");
            isWorking = false;
        }
    }

    /**
     * The createNewCityByUser method takes user input for parameters of a new City,
     * and creates a new City object using the input parameters.
     * The method returns an ArrayList of the parameters of the newly created City object.
     *
     * This method uses a try-catch block to handle NumberFormatException and
     * InputMismatchException, which are thrown when user input is not of the expected type.
     */
    public static ArrayList<Object> createNewCityByUser() {
        try {
            ArrayList<Object> parameters = new ArrayList<>();

            long id;
            id = parameters.hashCode();
            parameters.add(id);

            String name;
            do {
                System.out.print("Введите ненулевое имя(не может быть null!): ");
                name = scanner.nextLine().trim();
            } while (name.equals(""));
            parameters.add(name);

            Integer x;
            while (true) {
                try {
                    System.out.print("Введите координату x в типе данных Integer: ");
                    String nextLine = scanner.nextLine().trim();
                    if (nextLine.equals(""))
                        System.out.println("Введите ненулевую координату(не может быть null!)");
                    else {
                        x = Integer.parseInt(nextLine);
                        if (x <= 499)
                            break;
                        else
                            System.out.println("Максимальное значение координаты x: 499");
                    }
                } catch (NumberFormatException | InputMismatchException e2) {
                    System.out.println("Требуется ввести число в типе данных Integer!");
                }
            }
            double y;
            while (true) {
                try {
                    System.out.print("Введите координату y в типе данных double: ");
                    String nextLine = scanner.nextLine().trim();
                    if (nextLine.equals(""))
                        System.out.println("Введите ненулевую координату(не может быть null!)");
                    else {
                        y = Double.parseDouble(nextLine);
                        if (y > -274)
                            break;
                        else
                            System.out.println("Координата y должна быть больше -274");
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Требуется ввести число в типе данных double!");
                }
            }
            Coordinates coordinates = new Coordinates(x, y);
            parameters.add(coordinates);

            java.util.Date creationDate = java.sql.Date.valueOf(LocalDate.now());
            parameters.add(creationDate);

            Integer area;
            while (true) {
                try {
                    System.out.print("Введите область в типе данных Integer: ");
                    String nextLine = scanner.nextLine().trim();
                    if (!nextLine.equals("")) {
                        area = Integer.parseInt(nextLine);
                        if (area > 0) {
                            break;
                        } else {
                            System.out.println("Область должна быть больше 0!");
                        }
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Требуется ввести число в типе данных Integer!");
                }
            }
            parameters.add(area);


            int population;
            while (true) {
                try {
                    System.out.print("Введите численность населения в типе данных int: ");
                    String nextLine = scanner.nextLine().trim();
                    if (nextLine.equals(""))
                        System.out.println("Введите ненулевую численность населения(не может быть null!)");
                    else {
                        population = Integer.parseInt(nextLine);
                        if (population > 0) {
                            break;
                        } else {
                            System.out.println("Численность населения должна быть больше 0!");
                        }
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Требуется ввести число в типе данных int!");
                }
            }
            parameters.add(population);

            Double metersAboveSeaLevel;
            while (true) {
                try {
                    System.out.println("Введите высоту над уровнем моря в метрах в типе данных Double: ");
                    String nextLine = scanner.nextLine().trim();
                    if (!nextLine.equals("")) {
                        metersAboveSeaLevel = Double.parseDouble(nextLine);
                        break;
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Требуется ввести число в типе данных Double!");
                }
            }
            parameters.add(metersAboveSeaLevel);


            Climate climate;
            while (true) {
                climate = (Climate) requestEnum(Climate.values(), "тип");
                if (climate == null)
                    System.out.println("Некорректно введены данные!(не может быть null)");
                else
                    break;
            }
            parameters.add(climate);

            Government government;
            while (true) {
                government = (Government) requestEnum(Government.values(), "тип");
                if (government == null)
                    System.out.println("Некорректно введены данные!");
                else
                    break;
            }
            parameters.add(government);


            StandardOfLiving standardOfLiving;
            while (true) {
                standardOfLiving = (StandardOfLiving) requestEnum(StandardOfLiving.values(), "тип");
                if (standardOfLiving == null)
                    System.out.println("Некорректно введены данные!(не может быть null)");
                else
                    break;
            }
            parameters.add(standardOfLiving);


            Human governor;
            while (true) {
                System.out.print("Введите ненулевое имя governor(не может быть null!): ");
                String nextLine = scanner.nextLine().trim();
                if (nextLine.equals(""))
                    System.out.println("Введите ненулевое имя(не может быть null!)");
                else {
                    governor = new Human(nextLine);
                    break;
                }
            }
            parameters.add(governor);

            return parameters;
        } catch (NoSuchElementException e) {
            System.out.println("чего прогу ломаеш?");
            System.exit(0);
            return null;
        }
    }

    private static Object requestEnum(Object[] values, String name) {
        try {
            int i = 0;

            if (values[0] instanceof Climate)
                System.out.println("Выберите " + name + ":");
            for (Object value : values) {
                System.out.println("\t" + ++i + " - " + value.toString());
            }
            System.out.print("Введите целое число от 1 до " + values.length + ": ");
            String userAnswer;

            try {
                userAnswer = scanner.nextLine().trim();
                if (userAnswer.equals(""))
                    return null;
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести целое число!");
                return null;
            }

            int userAnswerInt = Integer.parseInt(userAnswer);
            if (userAnswerInt >= 1 && userAnswerInt <= i) {
                return values[userAnswerInt - 1];
            } else {
                System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
            return null;
        } catch (NoSuchElementException e) {
            System.out.println("чего прогу ломаеш?");
            System.exit(0);
            return null;
        }
    }

    public static void setIsWorking(boolean isWorking) {
        UserManager.isWorking = isWorking;
    }

    public static boolean isWorking() {
        return isWorking;
    }
}


