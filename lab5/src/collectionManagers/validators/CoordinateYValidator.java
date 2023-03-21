package collectionManagers.validators;

public class CoordinateYValidator implements Validator<Double> {
    public String getDescr() {
        return "value > -27";
    }
    @Override
    public boolean validate(Double value) {
        return value > -274 && value <= Double.MAX_VALUE;
    }
}
