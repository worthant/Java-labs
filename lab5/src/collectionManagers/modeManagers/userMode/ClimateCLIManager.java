package collectionManagers.modeManagers.userMode;

import collection.City.Climate;
import collectionManagers.modeManagers.ModeManager;
import collectionManagers.validators.InputValidator;
import collectionManagers.validators.Validator;
import exceptions.BuildObjectException;

import java.util.Scanner;

public class ClimateCLIManager implements ModeManager<Climate> {
    @Override
    public Climate buildObject() throws BuildObjectException {
        Climate climate;
        Scanner scanner = new Scanner(System.in);
        InputValidator<String> inputValidator = new InputValidator();
        String nextLine;
        System.out.println();


        inputValidator.canBeNull(true);
        climate = CLIManagerUtility.requestEnum(Climate.values(), Climate.getName(), scanner, inputValidator, );
        return climate;
    }
}
