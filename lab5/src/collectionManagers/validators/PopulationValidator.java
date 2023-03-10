package collectionManagers.validators;

public class PopulationValidator implements Validator<Integer> {
    public String getDescr() {
        return "";
    }
    @Override
    public boolean validate(Integer value) {
        return value > 0 && value < Integer.MAX_VALUE;
    }
}
