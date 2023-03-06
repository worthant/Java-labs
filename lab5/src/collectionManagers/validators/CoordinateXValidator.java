package collectionManagers.validators;

public class CoordinateXValidator implements Validator<Integer> {

    @Override
    public boolean validate(Integer value) {
        return value <= 499 && value <= Integer.MAX_VALUE;
    }
}
