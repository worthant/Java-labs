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
    @Override
    public boolean validate(String value) {
        return !value.isEmpty() && !value.isBlank();
    }
}
