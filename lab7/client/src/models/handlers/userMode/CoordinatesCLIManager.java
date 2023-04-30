package models.handlers.userMode;


import exceptions.BuildObjectException;
import models.Coordinates;
import models.handlers.ModeManager;
import models.validators.CoordinateXValidator;
import models.validators.CoordinateYValidator;
import models.validators.InputValidator;
import models.validators.Validator;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that implements the ModeManager interface for creating Coordinate objects via command-line interface.
 */
public class CoordinatesCLIManager implements ModeManager<Coordinates> {
    /**
     * Builds a Coordinates object via command-line interface, prompting the user for input.
     *
     * @author boris
     * @since 2.0
     * @return the created Coordinates object.
     * @throws BuildObjectException if there is an error building the object.
     */
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
