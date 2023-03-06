package collectionManagers.validators;

public class CoordinateYValidator implements Validator<double> {
    @Override
    public boolean validate(double value) {
        return value > -274 && value <= Double.MAX_VALUE;
    }
}
