package collectionManagers.validators;

/**
 * Implementation of validator for user input.
 *
 * @author boris
 */
public class InputValidator implements Validator<String>{
    /**
     * Checks if value not null and not blank.
     *
     * @param value name to validate
     * @return true/false -- matches the restrictions
     */
    boolean canBeNull = false;
    public String getDescr() {
        return "";
    }
    @Override
    public boolean validate(String value) {
        if (!canBeNull)
            return !value.isEmpty() && !value.isBlank();
        return true;
    }

    public void canBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
    }
}
