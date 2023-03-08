package collectionManagers.validators;

import collection.City.Climate;

public class ClimateValidator implements Validator<Climate>{

    @Override
    public boolean validate(Climate climate) {
        return climate == null;
    }
}
