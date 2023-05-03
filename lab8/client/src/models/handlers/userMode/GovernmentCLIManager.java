package models.handlers.userMode;


import exceptions.BuildObjectException;
import models.Government;
import models.handlers.ModeManager;
import models.validators.InputValidator;

import java.util.Scanner;

/**
 * The GovernmentCLIManager class is responsible for managing the Government mode in the user interface.
 * It implements the ModeManager interface for Government type and provides methods for building the object.
 *
 * @author boris
 * @since 2.0
 */
public class GovernmentCLIManager implements ModeManager<Government> {
    /**
     * This method builds the Government object using user input and returns the constructed object.
     *
     * @return The Government object built using user input.
     * @throws BuildObjectException If there is an error while constructing the object.
     */
    @Override
    public Government buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        System.out.println();

        inputValidator.canBeNull(false);
        EnumRequester<Government> enumRequester = new EnumRequester<>();
        Government government = enumRequester.requestEnum(Government.values(), Government.class.getSimpleName(), scanner, inputValidator);
        return government;
    }
}
