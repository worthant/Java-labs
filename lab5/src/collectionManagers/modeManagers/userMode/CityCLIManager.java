package collectionManagers.modeManagers.userMode;

import collection.City.*;
import collectionManagers.IdManager;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.*;
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
        InputValidator inputValidator = new InputValidator();
        String nextLine;
        System.out.println();

        // unique id
        city.setId(IdManager.generateId());

        // name
        String name = null;
        Validator<String> nameValidator = new NameValidator();
        inputValidator.canBeNull(false);
        while (true) {
            System.out.print("Enter name(not null!)(type: String) : ");
            nextLine = scanner.nextLine();

            if (inputValidator.validate(nextLine)) {
                name = nextLine;
                if (nameValidator.validate(name)) {
                    city.setName(name);
                    break;
                } else {
                    System.out.println("Value violates restrictions for field! Try again.");
                    System.out.println("Restrictions: " + nameValidator.getDescr());
                }
            } else System.out.println("Input should not be empty!(value is not null)");
        }


        // Coordinates
        CoordinatesCLIManager coordinatesCLIHandler = new CoordinatesCLIManager();
        city.setCoordinates(coordinatesCLIHandler.buildObject());

        // date
        java.util.Date creationDate = java.sql.Date.valueOf(LocalDate.now());
        city.setCreationDate(creationDate);


        // area
        Integer area;
        Validator<Integer> areaValidator = new AreaValidator();
        while (true) {
            System.out.print("Enter area(not null!)(type: Integer) : ");
            nextLine = scanner.nextLine();

            if (inputValidator.validate(nextLine)) {
                area = Integer.parseInt(nextLine);
                if (areaValidator.validate(area)) {
                    city.setArea(area);
                    break;
                } else {
                    System.out.println("Value violates restrictions for this field! Try again.");
                    System.out.println("Restrictions: " + areaValidator.getDescr());
                }
            } else System.out.println("Input should not be empty!(value is not null)");
        }


        // population
        int population;
        Validator<Integer> populationValidator = new PopulationValidator();
        while (true) {
            System.out.print("Enter population(not null!)(type: int) : ");
            nextLine = scanner.nextLine();

            if (inputValidator.validate(nextLine)) {
                population = Integer.parseInt(nextLine);
                if (populationValidator.validate(population)) {
                    city.setPopulation(population);
                    break;
                } else {
                    System.out.println("Value violates restrictions for this field! Try again.");
                    System.out.println("Restrictions: " + populationValidator.getDescr());
                }
            } else System.out.println("Input should not be empty!(value is not null)");
        }


        // metersAboveSeaLevel
        Double metersAboveSeaLevel;
        Validator<Double> metersAboveSeaLevelValidator = new MetersAboveSeaLevelValidator();
        while (true) {
            System.out.println("Enter meters above sea level(not null!)(type: Double)");
            nextLine = scanner.nextLine();

            if (inputValidator.validate(nextLine)) {
                metersAboveSeaLevel = Double.parseDouble(nextLine);
                if (metersAboveSeaLevelValidator.validate(metersAboveSeaLevel)) {
                    city.setMetersAboveSeaLevel(metersAboveSeaLevel);
                    break;
                } else {
                    System.out.println("Value violates restrictions for this field! Try again.");
                    System.out.println("Restrictions: " + metersAboveSeaLevelValidator.getDescr());
                }
            } else System.out.println("Input should not be empty!(value is not null)");
        }


        // climate
        ClimateCLIManager climateCLIManager = new ClimateCLIManager();
        city.setClimate(climateCLIManager.buildObject());

        // government
        GovernmentCLIManager governmentCLIManager = new GovernmentCLIManager();
        city.setGovernment(governmentCLIManager.buildObject());

        // standardOfLiving
        StandardOfLivingCLIManager standardOfLivingCLIManager = new StandardOfLivingCLIManager();
        city.setStandardOfLiving(standardOfLivingCLIManager.buildObject());


//        while (true) {
//            System.out.println();
//            climate = (Climate) requestEnum(Climate.values(), "тип");
//            if (climate == null)
//                System.out.println("Некорректно введены данные!(не может быть null)");
//            else
//                break;
//        }
//        city.setClimate(climate);
//
//        Government government;
//        while (true) {
//            government = (Government) requestEnum(Government.values(), "тип");
//            if (government == null)
//                System.out.println("Некорректно введены данные!");
//            else
//                break;
//        }
//        city.setGovernment(government);
//
//
//        StandardOfLiving standardOfLiving;
//        while (true) {
//            standardOfLiving = (StandardOfLiving) requestEnum(StandardOfLiving.values(), "тип");
//            if (standardOfLiving == null)
//                System.out.println("Некорректно введены данные!(не может быть null)");
//            else
//                break;
//        }
//        city.setStandardOfLiving(standardOfLiving);


        // Human
        HumanCLIManager humanCLIManager = new HumanCLIManager();
        city.setGovernor(humanCLIManager.buildObject());

//
//        while (true) {
//            System.out.print("Введите ненулевое имя governor(не может быть null!): ");
//            String nextLine = scanner.nextLine().trim();
//            if (nextLine.equals(""))
//                System.out.println("Введите ненулевое имя(не может быть null!)");
//            else {
//                governor = new Human(nextLine);
//                break;
//            }
//        }
//        city.setGovernor(governor);
//
//        return city;
//    }
        return city;
    }
}
