package collectionManagers.validators;

public class StandardOfLivingValidator implements Validator<StandardOfLivingValidator> {
    @Override
    public boolean validate(StandardOfLivingValidator value) {
        return value == null;
    }
}
