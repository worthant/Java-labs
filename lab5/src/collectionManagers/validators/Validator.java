package collectionManagers.validators;

/**
 * Base validator. You should implement it for usage.
 *
 * @param <T> Type of validation value.
 */
public interface Validator<T> {
    boolean canBeNull = false;

    /**
     * Provides validation method.
     *
     * @param value value to validate
     * @return true if value is validate. Otherwise false.
     */
    boolean validate(T value);

    String getDescr();
}
