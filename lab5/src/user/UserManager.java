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
        commandMap = CommandManager.getCommandMap();
        scanner = new Scanner(System.in);
        isWorking = true;
    }

    public static void requestCommand() {
        // start tracking commands to History
        History.initializeHistoryStack();

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
            History.pushToHistoryStack(command);
        } else {
            System.out.println("Команды " + command + " не существует!" +
                    "\nЧтобы посмотреть список доступных команд напишите: help");
        }
    }

    public static ArrayList<Object> createNewCityByUser() {

        ArrayList<Object> parameters = new ArrayList<>();

        String name;

        do {
            System.out.print("Введите ненулевое имя: ");
            name = scanner.nextLine().trim();
        } while (name.equals(""));
        parameters.add(name);

        Integer x;
        while (true) {
            try {
                System.out.print("Введите координату x в типе данных float: ");
                x = scanner.nextInt();
                if (x <= 499)
                    break;
                else
                    System.out.println("Максимальное значение координаты x: 499");
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных float!");
                scanner.next();
            }
        }
        double y;
        while (true) {
            try {
                System.out.print("Введите координату y в типе данных int: ");
                y = scanner.nextDouble();
                if (y > -274)
                    break;
                else
                    System.out.println("Координата y должна быть больше -274");
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных int!");
                scanner.next();
            }
        }
        Coordinates coordinates = new Coordinates(x, y);
        parameters.add(coordinates);

        LocalDate creationDate = LocalDate.now();
        parameters.add(creationDate);

        Long age;
        while (true) {
            try {
                System.out.print("Введите возраст в типе данных long: ");
                age = scanner.nextLong();
                if (age > 0) {
                    break;
                } else {
                    System.out.println("Возраст должен быть больше 0!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных long!");
                scanner.next();
            }
        }
        parameters.add(age);

//        DragonType type;
//        do {
//            type = (DragonType) requestEnum(DragonType.values(), "тип");
//        } while (type == null);
//        characteristics.add(type);
//
//        DragonCharacter character;
//
//        while (true) {
//            scanner = new Scanner(System.in);
//            System.out.println("Сделать характер null?");
//            System.out.print("Введите ответ да/нет: ");
//            String userAnswer = scanner.nextLine().strip();
//            if (userAnswer.equals("да")) {
//                character = null;
//                break;
//            } else if (userAnswer.equals(("нет"))) {
//                character = (DragonCharacter) requestEnum(DragonCharacter.values(), "характер");
//                break;
//            } else {
//                System.out.println("Требуется ввести да/нет!");
//            }
//        }
//
//        characteristics.add(character);
//
//        scanner = new Scanner(System.in);
//        String depth;
//        DragonCave cave;
//        while (true) {
//            System.out.print("Введите глубину пещеры в типе данных int: ");
//            depth = scanner.nextLine().strip();
//            if (depth.equals("")) {
//                cave = null;
//                break;
//            } else {
//                try {
//                    cave = new DragonCave(Integer.parseInt(depth));
//                    break;
//                } catch (NumberFormatException e) {
//                    System.out.println("Требуется ввести целое число!");
//                }
//            }
//        }
//
//        characteristics.add(cave);
            return parameters;
        }
//
//    private static Object requestEnum(Object[] values, String name) {
//
//        int i = 0;
//
//        if (values[0] instanceof DragonCharacter)
//
//            System.out.println("Выберите " + name + ":");
//        for (Object value : values) {
//            System.out.println("\t" + ++i + "-" + value.toString());
//        }
//        System.out.print("Введите целое число от 1 до " + values.length + ": ");
//        int userAnswer;
//
//        try {
//            userAnswer = scanner.nextInt();
//        } catch (InputMismatchException e) {
//            System.out.println("Требуется ввести целое число!");
//            return null;
//        }
//
//        if (userAnswer >= 1 && userAnswer <= i) {
//            return values[userAnswer - 1];
//        } else {
//            System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
//            return null;
//        }
//
//    }

    public static void setIsWorking(boolean isWorking) {
        UserManager.isWorking = isWorking;
    }

    public static boolean isWorking() {
        return isWorking;
    }
}

