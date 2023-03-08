package collectionManagers.validators;

public class PopulationValidator implements Validator<Integer> {
    @Override
    public boolean validate(Integer value) {
        return value > 0 && value < Integer.MAX_VALUE;
    }
}
