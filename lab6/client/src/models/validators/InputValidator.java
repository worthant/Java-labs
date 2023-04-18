package models.validators;

/**
 * Implementation of validator for user input.
 *
 * @since 2.0
 * @author boris
 */
public class InputValidator{
    /**
     * Checks if value not null and not blank.
     *
     * @param value name to validate
     * @return true/false -- matches the restrictions
     */
    boolean canBeNull = false;
    public String getDescr() {
        return "Validates user input";
    }
    public boolean validate(String value) {
        if (!canBeNull)
            return !value.isEmpty() && !value.isBlank();
        return true;
    }

    public void canBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
    }
}
