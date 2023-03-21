package collectionManagers.validators;

public class MetersAboveSeaLevelValidator implements Validator<Double> {
    public String getDescr() {
        return "any value, that fits Double format";
    }
    @Override
    public boolean validate(Double value) {
        return value < Double.MAX_VALUE;
    }
}
