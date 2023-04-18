package models.handlers;

import exceptions.BuildObjectException;

/**
 * Interface for mode managers that can generate objects.
 *
 * @param <T> The type of object this manager generates.
 */
public interface ModeManager<T> {
    /**
     * Generates a new object of type T.
     *
     * @return The newly created object.
     * @throws BuildObjectException If an error occurred during object creation.
     */
    T buildObject() throws BuildObjectException;
}
