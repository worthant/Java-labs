package collectionManagers.modeManagers.nonUserMode;

import collection.City.City;
import collectionManagers.modeManagers.ModeManager;
import exceptions.BuildObjectException;

import java.util.ArrayList;
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
        int valuesToRead = 12;
        int coordsIndex = 1;
        int fromIndex = 3;
        int toIndex = 7;
        ArrayList<String> values = new ArrayList<>(valuesToRead);

        for (int i = 0; i < valuesToRead && scanner.hasNextLine(); i++)
        {
            String line = scanner.nextLine();
            if (!line.isEmpty())
                values.add(line);
            else
            {
                values.add(null);

                if (i == coordsIndex)
                {
                    valuesToRead -= 1;
                    fromIndex -= 1;
                    toIndex -= 1;
                }

                if (i == fromIndex)
                {
                    valuesToRead -= 3;
                    toIndex -= 3;
                }

                if (i == toIndex)
                {
                    valuesToRead -= 3;
                }
            }
        }

        try {
//            result.setId(RouteHandlers.generateID());
//            result.setName(values.get(0));
//            System.out.println("Name: " + result.getName());
//            if (values.get(coordsIndex) != null) {
//                System.out.println("Generating coordinates...");
//                Coordinates coordinates = new Coordinates();
//                coordinates.setX(Double.parseDouble(values.get(coordsIndex)));
//                System.out.println("Coords X: " + coordinates.getX());
//                coordinates.setY(Float.valueOf(values.get(coordsIndex + 1)));
//                System.out.println("Coords Y: " + coordinates.getY());
//                result.setCoordinates(coordinates);
//            }
//            System.out.println("Coords: " + result.getCoordinates());
//            result.setFrom(generateLocation(fromIndex, values));
//            System.out.println("From: " + result.getFrom());
//            result.setTo(generateLocation(toIndex, values));
//            System.out.println("To: " + result.getTo());
//            result.setDistance(Integer.parseInt(values.get(values.size() - 1)));
//            System.out.println("Distance: " + result.getDistance());
//            result.setCreationDate(Date.from(Instant.now()));
//            System.out.println("Generated at: " + result.getCreationDate());
//            System.out.println("Object generated! Validating result...");
//
//            Validator<Route> validator = new RouteValidator();
//            if (!validator.validate(result))
//            {
//                System.out.println("Object's invalid, skipping...");
//                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
//            }
//            System.out.println("Validate successful! Sending result...");

            return result;

        } catch (NumberFormatException | NullPointerException e)
        {
            throw new BuildObjectException("Предоставленные данные для построения объекта неверны. Воспользуйтесь ручным вводом или исправьте команду в скрипте.");
        }
    }
}
