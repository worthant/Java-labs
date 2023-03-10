package collectionManagers.validators;

public class StandardOfLivingValidator implements Validator<StandardOfLivingValidator> {
    public String getDescr() {
        return "";
    }
    @Override
    public boolean validate(StandardOfLivingValidator value) {
        return value == null;
    }
}
