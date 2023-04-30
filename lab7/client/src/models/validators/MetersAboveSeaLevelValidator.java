package models.validators;

/**
 * Implementation of validator for MetersAboveSeaLevel.
 *
 * @since 2.0
 * @author boris
 */
public class MetersAboveSeaLevelValidator implements Validator<Double> {
    public String getDescr() {
        return "any value, that fits Double format";
    }
    @Override
    public boolean validate(Double value) {
        return value < Double.MAX_VALUE;
    }
}
