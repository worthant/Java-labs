package models.validators;

/**
 * Implementation of validator for CoordinateY
 *
 *  @since 2.0
 *  @author boris
 */
public class CoordinateYValidator implements Validator<Double> {
    public String getDescr() {
        return "Constraints: 0 <= y <= 1000";
    }
    @Deprecated
    public boolean validateOld(Double value) {
        return value > -274 && value <= Double.MAX_VALUE;
    }

    @Override
    public boolean validate(Double value) {return value >= 0 & value <= 1000;}
}
