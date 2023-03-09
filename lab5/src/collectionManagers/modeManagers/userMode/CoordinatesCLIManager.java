package collectionManagers.modeManagers.userMode;

import collection.City.Coordinates;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.CoordinateXValidator;
import collectionManagers.validators.CoordinateYValidator;
import collectionManagers.validators.InputValidator;
import collectionManagers.validators.Validator;
import exceptions.BuildObjectException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CoordinatesCLIManager implements ModeManager<Coordinates> {
    @Override
    public Coordinates buildObject() throws BuildObjectException {
        try {
            System.out.println("Generating Coordinates...");
            Coordinates coordinates = new Coordinates();
            Scanner scanner = new Scanner(System.in);
            Validator<String> inputValidator = new InputValidator();
            String nextLine;
            System.out.println();


            // coordinate x
            Integer x = null;
            Validator<Integer> coordXValidator = new CoordinateXValidator();
            do {
                System.out.print("Enter coordinate x(not null!)(type: Integer) : ");
                nextLine = scanner.nextLine();
                if (inputValidator.validate(nextLine)) {
                    x = Integer.parseInt(nextLine);
                    if (coordXValidator.validate(x))
                        coordinates.setX(x);
                    else {
                        System.out.println("Value violates restrictions for this field! Try again.");
                        System.out.println("Restrictions: Should be not null or empty.");
                    }
                } else {
                    System.out.println("Input should not be empty!(value is not null)");
                }
            } while (!inputValidator.validate(nextLine));

            // coordinate y
            Double y = null;
            Validator<Double> coordYValidator = new CoordinateYValidator();
            do {
                System.out.print("Enter coordinate y(not null!)(type: Integer) : ");
                nextLine = scanner.nextLine();
                if (inputValidator.validate(nextLine)) {
                    y = Double.parseDouble(nextLine);
                    if (coordYValidator.validate(y))
                        coordinates.setY(y);
                    else {
                        System.out.println("Value violates restrictions for this field! Try again.");
                        System.out.println("Restrictions: Should be not null or empty.");
                    }
                } else {
                    System.out.println("Input should not be empty!(value is not null)");
                }
            } while (!inputValidator.validate(nextLine));
            return coordinates;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
