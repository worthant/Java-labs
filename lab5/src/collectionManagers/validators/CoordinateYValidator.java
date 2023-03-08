package collectionManagers.validators;

public class CoordinateYValidator implements Validator<Double> {
    @Override
    public boolean validate(Double value) {
        return value > -274 && value <= Double.MAX_VALUE;
    }
}
