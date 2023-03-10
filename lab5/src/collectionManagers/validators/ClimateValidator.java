package collectionManagers.validators;

import collection.City.Climate;

public class ClimateValidator implements Validator<Climate>{
    public String getDescr() {
        return "";
    }
    @Override
    public boolean validate(Climate climate) {
        return climate == null;
    }
}
