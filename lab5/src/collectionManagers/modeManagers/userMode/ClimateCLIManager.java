package collectionManagers.modeManagers.userMode;

import collection.City.Climate;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.InputValidator;
import exceptions.BuildObjectException;

import java.util.Scanner;

public class ClimateCLIManager implements ModeManager<Climate> {
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
