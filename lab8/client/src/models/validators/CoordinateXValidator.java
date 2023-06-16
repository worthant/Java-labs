package models.validators;

/**
 * Implementation of validator for CoordinateX
 *
 *  @since 2.0
 *  @author boris
 */
public class CoordinateXValidator implements Validator<Integer> {
    public String getDescr() {
        return "Constraints: 0 <= x <= 1000";
    }
    @Deprecated
    public boolean validateOld(Integer value) {
        return value <= 499;
    }

    @Override
    public boolean validate(Integer value) {return value >= 0 & value <= 1000;}
}
