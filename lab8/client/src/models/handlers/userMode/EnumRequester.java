package models.handlers.userMode;

import exceptions.BuildObjectException;
import models.validators.InputValidator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A utility class for requesting an enum value from the user.
 *
 * @param <T> the type of the enum to request
 */
public class EnumRequester<T extends Enum<T>> {

    /**
     * Requests an enum value from the user based on the provided array of enum values.
     *
     * @author boris
     * @since 2.0
     *
     * @param values the array of enum values to choose from
     * @param enumName the name of the enum type being requested
     * @param scanner the scanner to read user input from
     * @param inputValidator the input validator to validate user input
     * @return the selected enum value
     * @throws BuildObjectException if an error occurs during object construction
     */
    public T requestEnum(T[] values, String enumName, Scanner scanner, InputValidator inputValidator) throws BuildObjectException {
        try {
            System.out.println("Choose " + enumName + " type:");

            int i = 0;
            for (T value : values) {
                System.out.println("\t" + ++i + " - " + value.toString());
            }

            String nextLine;
            Integer userAnswer;
            while (true) {
                System.out.print("Enter number from 1 to " + values.length + ": ");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    userAnswer = Integer.valueOf(nextLine);
                    if (userAnswer >= 1 && userAnswer <= i)
                        return values[userAnswer - 1];
                    else System.out.println("Enter number from 1 to " + values.length + "!");
                } else System.out.println("Input should not be empty!(value is not null)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
            return null;
        } catch (NoSuchElementException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}