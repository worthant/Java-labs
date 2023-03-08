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
    }
}
