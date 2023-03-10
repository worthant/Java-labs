package collectionManagers.validators;

import collection.City.Government;

public class GovernmentValidator implements Validator<Government> {
    public String getDescr() {
        return "";
    }
    @Override
    public boolean validate(Government value) {
        return value == null;
    }
}
