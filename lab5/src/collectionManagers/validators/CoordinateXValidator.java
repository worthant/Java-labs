package collectionManagers.validators;

public class CoordinateXValidator implements Validator<Integer> {
    public String getDescr() {
        return "";
    }
    @Override
    public boolean validate(Integer value) {
        return value <= 499 && value <= Integer.MAX_VALUE;
    }
}
