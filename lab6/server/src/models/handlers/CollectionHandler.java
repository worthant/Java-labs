package models.handlers;

import java.util.AbstractCollection;
import java.util.Date;

/**
 * Base interface for handling Collection of Elements. You should implement it in your handler for correct command working.
 *
 * @param <T> Type of Collection
 * @param <E> Type of Elements
 * @author worthant
 * @since 1.0
 */
public interface CollectionHandler<T extends AbstractCollection<E>, E> {
    /**
     * Provides method for get collection.
     *
     * @return Actual collection reference
     */
    T getCollection();

    /**
     * Provides method for set collection.
     *
     * @param value Collection
     */
    void setCollection(T value);

    /**
     * Provides method for fast adding element in collection.
     *
     * @param value Element to add
     */
    void addElementToCollection(E value);

    /**
     * Provides method for fast clear element in collection.
     */
    void clearCollection();


    /**
     * Provides method for fast getting first element.
     *
     * @return First element. If collection was empty, creates new Element and returns it.
     */
    E getFirstOrNew();

    /**
     * Provides method for get collection initDate.
     *
     * @return Date instance -- Collection created time.
     * @see commandManager.commands.InfoCommand
     */
    Date getInitDate();
}