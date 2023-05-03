package models.validators;

/**
 * Implementation of validator for CoordinateX
 *
 *  @since 2.0
 *  @author boris
 */
public class CoordinateXValidator implements Validator<Integer> {
    public String getDescr() {
        return "x <= 499";
    }
    @Override
    public boolean validate(Integer value) {
        return value <= 499;
    }
}
