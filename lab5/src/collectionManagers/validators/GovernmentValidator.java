package collectionManagers.validators;

import collection.City.Government;

public class GovernmentValidator implements Validator<Government> {
    @Override
    public boolean validate(Government value) {
        return value == null;
    }
}
