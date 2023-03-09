package collectionManagers.modeManagers.userMode;

import collection.City.*;
import collectionManagers.IdManager;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.NameValidator;
import collectionManagers.validators.Validator;
import exceptions.BuildObjectException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CityCLIManager implements ModeManager<City> {
    @Override
    public City buildObject() throws BuildObjectException {
        System.out.println("Building new City object...");
        City city = new City();
        Scanner scanner = new Scanner(System.in);

        // unique id
        city.setId(IdManager.generateId());

        // name
        String name;
        Validator<String> nameValidator = new NameValidator();
        do {
            System.out.print("Enter name(not null!)(type: String) : ");
            name = scanner.nextLine();

            // if this value will be null add this code:
            // if (name.isEmpty()) name = inputLine, where inputLine is *name* = scanner.nextLine()
            // and also remove value == null checking from Validator

            if (!nameValidator.validate(name)) {
                System.out.println("Value violates restrictions for field! Try again.");
                System.out.println("Restrictions: Should be not null and not empty.");
            }
        } while (!nameValidator.validate(name));
        city.setName(name);

        // Coordinates
        CoordinatesCLIManager coordinatesCLIHandler = new CoordinatesCLIManager();
        city.setCoordinates(coordinatesCLIHandler.buildObject());

        // date
        java.util.Date creationDate = java.sql.Date.valueOf(LocalDate.now());
        city.setCreationDate(creationDate);

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
        city.setArea(area);


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
        city.setPopulation(population);

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
        city.setMetersAboveSeaLevel(metersAboveSeaLevel);


        Climate climate;
        while (true) {
            System.out.println();
            climate = (Climate) requestEnum(Climate.values(), "тип");
            if (climate == null)
                System.out.println("Некорректно введены данные!(не может быть null)");
            else
                break;
        }
        city.setClimate(climate);

        Government government;
        while (true) {
            government = (Government) requestEnum(Government.values(), "тип");
            if (government == null)
                System.out.println("Некорректно введены данные!");
            else
                break;
        }
        city.setGovernment(government);


        StandardOfLiving standardOfLiving;
        while (true) {
            standardOfLiving = (StandardOfLiving) requestEnum(StandardOfLiving.values(), "тип");
            if (standardOfLiving == null)
                System.out.println("Некорректно введены данные!(не может быть null)");
            else
                break;
        }
        city.setStandardOfLiving(standardOfLiving);


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
        city.setGovernor(governor);

        return city;
    }

    private static Object requestEnum(Object[] values, String name) {
        try {
            System.out.println("Выберите " + name + ":");

            int i = 0;
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
}
