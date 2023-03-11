package collectionManagers.modeManagers.userMode;

import collectionManagers.validators.Validator;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CLIManagerUtility {
    private static Object requestEnum(Object[] values, String enumName, Scanner scanner, Validator<String> inputValidator, Validator<Object> enumValidator) {
        try {
            System.out.println("Choose " + enumName + " type:");

            int i = 0;
            for (Object value : values) {
                System.out.println("\t" + ++i + " - " + value.toString());
            }

            String nextLine;
            Integer userAnswer;
            while (true) {
                System.out.print("Enter number from 1 to " + values.length + ": ");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    userAnswer = Integer.parseInt(nextLine);
                    if (enumValidator.validate(userAnswer))
                        break;
                    else {
                        System.out.println("Value violates restrictions for this field! Try again.");
                        System.out.println("Restrictions: " + enumValidator.getDescr());
                    }
                } else System.out.println("Input should not be empty!(value is not null)");
            }



//            catch (InputMismatchException e)

            if (userAnswer >= 1 && userAnswer <= i) {
                return values[userAnswer - 1];
            } else {
                System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
                return null;
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
