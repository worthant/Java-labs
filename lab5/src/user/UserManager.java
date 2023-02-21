package user;

import command.Command;
import command.CommandManager;
import collection.City.*;
import collectionManagers.*;
import command.commands.History;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserManager {

    private static HashMap<String, Command> commandMap;
    private static Scanner scanner;
    private static boolean isWorking;

    static {
        History.initializeCommandsHistoryQueue();
        commandMap = CommandManager.getCommandMap();
        scanner = new Scanner(System.in);
        isWorking = true;
    }

    public static void requestCommand() {

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
    }

    public static ArrayList<Object> createNewCityByUser() {

        ArrayList<Object> parameters = new ArrayList<>();

//      TODO: Generate id automatically;
//          Remember, that it should be unique!


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
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных Integer!");
                scanner.next();
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
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных double!");
                scanner.next();
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
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных Integer!");
                scanner.next();
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
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных int!");
                scanner.next();
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
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных Double!");
                scanner.next();
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
                System.out.println("Некорректно введены данные!(не может быть null)");
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


        String governor;
        do {
            System.out.print("Введите ненулевое имя governor(не может быть null!): ");
            governor = scanner.nextLine().trim();
        } while (governor.equals(""));
        parameters.add(governor);


        return parameters;
    }

    private static Object requestEnum(Object[] values, String name) {

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
    }

    public static void setIsWorking(boolean isWorking) {
        UserManager.isWorking = isWorking;
    }

    public static boolean isWorking() {
        return isWorking;
    }
}


