package models.handlers.userMode;


import exceptions.BuildObjectException;
import main.Utilities;
import models.City;
import models.handlers.ModeManager;
import models.validators.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements the ModeManager interface for City objects.
 * It contains methods for building a new City object using user input through the command line interface.
 * It handles input validation for all required fields, and uses other CLIManagers to obtain nested objects.
 *
 * @author boris
 * @since 2.0
 * @see ModeManager
 * @see City
 */
public class CityCLIManager implements ModeManager<City> {

    /**
     * Builds a new City object using user input through the command line interface.
     * It handles input validation for all required fields, and uses other CLIManagers to obtain nested objects.
     *
     * @return a new City object built from user input
     * @throws BuildObjectException if an error occurs during object construction
     *
     * @author boris
     * @since 2.0
     *
     * @see CoordinatesCLIManager
     * @see ClimateCLIManager
     * @see GovernmentCLIManager
     * @see StandardOfLivingCLIManager
     * @see HumanCLIManager
     */
    @Override
    public City buildObject() throws BuildObjectException {
        try {
            System.out.println("Building new City object...");
            City city = new City();
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();

            // unique id
            city.setId(Utilities.generateId());

            // name
            String name;
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
            // city.setCreationDate(Date.from(Instant.now()));

            // area
            Integer area;
            Validator<Integer> areaValidator = new AreaValidator();
            while (true) {
                try {
                    System.out.print("Enter area(not null!)(type: Integer) : ");
                    nextLine = scanner.nextLine();

                    if (inputValidator.validate(nextLine)) {
                        area = Integer.valueOf(nextLine);
                        if (areaValidator.validate(area)) {
                            city.setArea(area);
                            break;
                        } else {
                            System.out.println("Value violates restrictions for this field! Try again.");
                            System.out.println("Restrictions: " + areaValidator.getDescr());
                        }
                    } else System.out.println("Input should not be empty!(value is not null)");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                }
            }

            // population
            int population;
            Validator<Integer> populationValidator = new PopulationValidator();
            while (true) {
                try {
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
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                }
            }

            // metersAboveSeaLevel
            Double metersAboveSeaLevel;
            Validator<Double> metersAboveSeaLevelValidator = new MetersAboveSeaLevelValidator();
            while (true) {
                try {
                    System.out.println("Enter meters above sea level(not null!)(type: Double) : ");
                    nextLine = scanner.nextLine();

                    if (inputValidator.validate(nextLine)) {
                        metersAboveSeaLevel = Double.valueOf(nextLine);
                        if (metersAboveSeaLevelValidator.validate(metersAboveSeaLevel)) {
                            city.setMetersAboveSeaLevel(metersAboveSeaLevel);
                            break;
                        } else {
                            System.out.println("Value violates restrictions for this field! Try again.");
                            System.out.println("Restrictions: " + metersAboveSeaLevelValidator.getDescr());
                        }
                    } else System.out.println("Input should not be empty!(value is not null)");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                }
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

            // Human
            HumanCLIManager humanCLIManager = new HumanCLIManager();
            city.setGovernor(humanCLIManager.buildObject());

            return city;

        } catch (NoSuchElementException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
