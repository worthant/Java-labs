package collectionManagers.validators;

public class CoordinateXValidator implements Validator<Integer> {
    public String getDescr() {
        return "x <= 499";
    }
    @Override
    public boolean validate(Integer value) {
        return value <= 499;
    }
}
