package collectionManagers.validators;

/**
 * Implementation of validator for name field. (City)
 *
 * @author boris
 */
public class NameValidator implements Validator<String> {

    /**
     * Checks if value not null and not blank.
     *
     * @see collection.City
     * @param value name to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(String value) {
        return value != null && !value.isEmpty() && !value.isBlank();
    }
}