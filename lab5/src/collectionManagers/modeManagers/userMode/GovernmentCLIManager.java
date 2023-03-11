package collectionManagers.modeManagers.userMode;

import collection.City.Climate;
import collection.City.Government;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.InputValidator;
import exceptions.BuildObjectException;

import java.util.Scanner;

public class GovernmentCLIManager implements ModeManager<Government> {
    @Override
    public Government buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        System.out.println();

        inputValidator.canBeNull(false);
        EnumRequester<Government> enumRequester = new EnumRequester<>();
        Government government = enumRequester.requestEnum(Government.values(), Government.getName(), scanner, inputValidator);
        return government;
    }
}
