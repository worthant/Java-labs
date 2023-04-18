package models.handlers.userMode;


import exceptions.BuildObjectException;
import models.Climate;
import models.handlers.ModeManager;
import models.validators.InputValidator;

import java.util.Scanner;

/**
 * The ClimateCLIManager class is responsible for managing the Climate mode in the user interface.
 * It implements the ModeManager interface for Climate type and provides methods for building the object.
 *
 * @author boris
 * @since 2.0
 */
public class ClimateCLIManager implements ModeManager<Climate> {
    /**
     * This method builds the Climate object using user input and returns the constructed object.
     *
     * @return The Climate object built using user input.
     * @throws BuildObjectException If there is an error while constructing the object.
     */
    @Override
    public Climate buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        System.out.println();

        inputValidator.canBeNull(true);
        EnumRequester<Climate> enumRequester = new EnumRequester<>();
        Climate climate = enumRequester.requestEnum(Climate.values(), Climate.class.getSimpleName(), scanner, inputValidator);
        return climate;
    }
}
