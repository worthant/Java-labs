package collectionManagers.validators;

public class PopulationValidator implements Validator<int> {
    @Override
    public boolean validate(int value) {
        return value > 0 && value < Integer.MAX_VALUE;
    }
}
