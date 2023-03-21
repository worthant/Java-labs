package collectionManagers.modeManagers.userMode;

import collection.City.Coordinates;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.CoordinateXValidator;
import collectionManagers.validators.CoordinateYValidator;
import collectionManagers.validators.InputValidator;
import collectionManagers.validators.Validator;
import exceptions.BuildObjectException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CoordinatesCLIManager implements ModeManager<Coordinates> {
    @Override
    public Coordinates buildObject() throws BuildObjectException {
        try {
            System.out.println("Generating Coordinates...");
            Coordinates coordinates = new Coordinates();
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();


            // coordinate x
            Integer x;
            Validator<Integer> coordXValidator = new CoordinateXValidator();
            while(true) {
                try {
                    System.out.print("Enter coordinate x(not null!)(type: Integer) : ");
                    nextLine = scanner.nextLine();
                    if (inputValidator.validate(nextLine)) {
                        x = Integer.parseInt(nextLine);
                        if (coordXValidator.validate(x)) {
                            coordinates.setX(x);
                            break;
                        } else {
                            System.out.println("Value violates restrictions for this field! Try again.");
                            System.out.println("Restrictions: " + coordXValidator.getDescr());
                        }
                    } else System.out.println("Input should not be empty!(value is not null)");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                }
            }

            // coordinate y
            Double y;
            Validator<Double> coordYValidator = new CoordinateYValidator();
            while(true) {
                try {
                    System.out.print("Enter coordinate y(not null!)(type: Double) : ");
                    nextLine = scanner.nextLine();
                    if (inputValidator.validate(nextLine)) {
                        y = Double.parseDouble(nextLine);
                        if (coordYValidator.validate(y)) {
                            coordinates.setY(y);
                            break;
                        } else {
                            System.out.println("Value violates restrictions for this field! Try again.");
                            System.out.println("Restrictions: " + coordYValidator.getDescr());
                        }
                    } else System.out.println("Input should not be empty!(value is not null)");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                }
            }
            return coordinates;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
