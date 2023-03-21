package collectionManagers.modeManagers.userMode;

import collection.City.Human;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.InputValidator;
import exceptions.BuildObjectException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanCLIManager implements ModeManager<Human> {
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
