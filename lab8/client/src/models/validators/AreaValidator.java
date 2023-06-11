package models.validators;

/**
 * Implementation of validator for Area
 *
 *  @since 2.0
 *  @author boris
 */
public class AreaValidator implements Validator<Integer> {
    public String getDescr() {
        return "Constraints: should be greater than 0";
    }
    @Override
    public boolean validate(Integer value) {
        return value > 0 && value < Integer.MAX_VALUE;
    }
}
