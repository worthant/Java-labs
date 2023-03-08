package collectionManagers.modeManagers;

import exceptions.BuildObjectException;

public interface ModeManager<T> {
    /**
     * Provides method to generate objects.
     *
     * @return Created object.
     */
    T buildObject() throws BuildObjectException;
}
