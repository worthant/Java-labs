package collectionManagers.modeManagers.nonUserMode;

import collection.City.*;
import collectionManagers.IdManager;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.CityValidator;
import collectionManagers.validators.Validator;
import exceptions.BuildObjectException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CityNonUserManager implements ModeManager<City> {

    Scanner scanner;

    /**
     * Constructor for setup handler's scanner.
     *
     * @param scanner Command scanner for reading argument
     */
    public CityNonUserManager(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public City buildObject() throws BuildObjectException {
        System.out.println("Generating object...");
        City result = new City();
        int valuesToRead = 10;
        ArrayList<String> values = new ArrayList<>(valuesToRead);

        for (int i = 0; i < valuesToRead && scanner.hasNextLine(); i++) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty())
                values.add(line);
            else
                values.add(null);
        }

        try {
            // id
            result.setId(IdManager.generateId());

            // name
            result.setName(values.get(0));
            System.out.println("Name: " + result.getName());

            // coordinates
            System.out.println("Generating coordinates...");
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Integer.valueOf(values.get(1)));
            System.out.println("Coords X: " + coordinates.getX());
            coordinates.setY(Double.valueOf(values.get(2)));
            System.out.println("Coords Y: " + coordinates.getY());
            result.setCoordinates(coordinates);
            System.out.println("Coords: " + result.getCoordinates());

            // date
            result.setCreationDate(Date.from(Instant.now()));
            System.out.println("Generated at: " + result.getCreationDate());
            System.out.println("Object generated! Validating result...");

            // area
            result.setArea(Integer.valueOf(values.get((3))));
            System.out.println("Area: " + result.getArea());

            // population
            result.setPopulation(Integer.parseInt(values.get(4)));
            System.out.println("Population: " + result.getPopulation());

            // metersAboveSeaLevel
            result.setMetersAboveSeaLevel(Double.valueOf(values.get(5)));
            System.out.println("MetersAboveSeaLevel: " + result.getMetersAboveSeaLevel());

            // climate
            int i = Climate.values().length;
            int userAnswer = Integer.parseInt(values.get(6));
            if (userAnswer >= 1 && userAnswer <= i)
                result.setClimate(Climate.values()[userAnswer - 1]);
            else {
                System.out.println("Object's invalid, skipping...");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("Climate: " + Climate.values()[userAnswer - 1]);

            // government
            i = Government.values().length;
            userAnswer = Integer.parseInt(values.get(7));
            if (userAnswer >= 1 && userAnswer <= i)
                result.setGovernment(Government.values()[userAnswer - 1]);
            else {
                System.out.println("Object's invalid, skipping...");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("Government: " + Government.values()[userAnswer - 1]);

            // standardOfLiving
            i = StandardOfLiving.values().length;
            userAnswer = Integer.parseInt(values.get(8));
            if (userAnswer >= 1 && userAnswer <= i)
                result.setStandardOfLiving(StandardOfLiving.values()[userAnswer - 1]);
            else {
                System.out.println("Object's invalid, skipping...");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("StandardOfLiving: " + StandardOfLiving.values()[userAnswer - 1]);

            // Human
            Human human = new Human();
            human.setName(values.get(9));
            result.setGovernor(human);
            System.out.println("Human: " + human.getName());

            Validator<City> validator = new CityValidator();
            if (!validator.validate(result)) {
                System.out.println("Object's invalid, skipping...");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("Validate successful! Sending result...");

            return result;

        } catch (NumberFormatException | NullPointerException e) {
            throw new BuildObjectException("Предоставленные данные для построения объекта неверны. Воспользуйтесь ручным вводом или исправьте команду в скрипте.");
        }
    }
}
