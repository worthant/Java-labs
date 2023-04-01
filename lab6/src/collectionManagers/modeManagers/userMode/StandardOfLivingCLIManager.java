package collectionManagers.modeManagers.userMode;

import collection.City.StandardOfLiving;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.InputValidator;
import exceptions.BuildObjectException;

import java.util.Scanner;

/**
 * The StandardOfLiving class is responsible for managing the StandardOfLiving mode in the user interface.
 * It implements the ModeManager interface for Government type and provides methods for building the object.
 *
 * @author boris
 * @since 2.0
 */
public class StandardOfLivingCLIManager implements ModeManager<StandardOfLiving> {

    /**
     * This method builds the StandardOfLiving object using user input and returns the constructed object.
     *
     * @return The StandardOfLiving object built using user input.
     * @throws BuildObjectException If there is an error while constructing the object.
     */
    @Override
    public StandardOfLiving buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        System.out.println();

        inputValidator.canBeNull(false);
        EnumRequester<StandardOfLiving> enumRequester = new EnumRequester<>();
        StandardOfLiving standardOfLiving = enumRequester.requestEnum(StandardOfLiving.values(), StandardOfLiving.class.getSimpleName(), scanner, inputValidator);
        return standardOfLiving;
    }
}
