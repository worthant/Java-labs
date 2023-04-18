package models.handlers.userMode;


import exceptions.BuildObjectException;
import models.Human;
import models.handlers.ModeManager;
import models.validators.InputValidator;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * This class implements the ModeManager interface for constructing a new Human object through the CLI.
 * The buildObject() method prompts the user to enter the name of the human governor and returns a new Human object with the entered name.
 * The input is validated using an InputValidator object.
 *
 * @author boris
 * @since 2.0
 */
public class HumanCLIManager implements ModeManager<Human> {
    /**
     * Constructs a new Human object through the CLI by prompting the user to enter the name of the governor.
     * @return a new Human object with the entered name
     * @throws BuildObjectException if an error occurs while constructing the object
     */
    @Override
    public Human buildObject() throws BuildObjectException {
        try {
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();

            inputValidator.canBeNull(false);
            Human governor = new Human();
            while (true) {
                System.out.print("Enter name of governor(not null!)(type: String) : ");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    governor.setName(nextLine);
                    break;
                } else System.out.println("Input should not be empty!(value is not null)");
            }
            return governor;
        } catch (NoSuchElementException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
