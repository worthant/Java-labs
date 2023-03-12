package collectionManagers.modeManagers.userMode;

import collectionManagers.validators.InputValidator;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EnumRequester<T extends Enum<T>> {
    public T requestEnum(T[] values, String enumName, Scanner scanner, InputValidator inputValidator) {
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
            System.out.println("чего прогу ломаеш?");
            System.exit(0);
            return null;
        }
    }
}