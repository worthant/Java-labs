package collectionManagers.modeManagers.userMode;

import collection.City.Government;
import collection.City.StandardOfLiving;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.InputValidator;
import exceptions.BuildObjectException;

import java.util.Scanner;

public class StandardOfLivingCLIManager implements ModeManager<StandardOfLiving> {
    @Override
    public StandardOfLiving buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        System.out.println();

        inputValidator.canBeNull(false);
        EnumRequester<StandardOfLiving> enumRequester = new EnumRequester<>();
        StandardOfLiving standardOfLiving = enumRequester.requestEnum(StandardOfLiving.values(), StandardOfLiving.getName(), scanner, inputValidator);
        return standardOfLiving;
    }
}
