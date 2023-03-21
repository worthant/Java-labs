package collectionManagers;

import java.util.AbstractCollection;
import java.util.Comparator;

public interface CollectionManager<T extends AbstractCollection<E>, E> {
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
     * Provides method for fast getting first element.
     *
     * @return First element. If collection was empty, creates new Element and returns it.
     */
    E getFirstOrNew();

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
     * Provides method for fast sorting collection. You can ignore this method.
     */
    void sort();

    /**
     * Provides method for getting last element.
     *
     * @return Last element. If collection was empty, returns null.
     */
    E getLastElement();

    /**
     * Provides method for validate elements in collection. You can ignore this method
     */
    void validateElements();

    /**
     * Gets min element by given comparator
     *
     * @param comparator Comparator to compare.
     * @return Min element or null if collection is empty
     */
    E getMin(Comparator<E> comparator);

    /**
     * Gets max element by given comparator
     *
     * @param comparator Comparator to compare.
     * @return Max element or null if collection is empty
     */
    E getMax(Comparator<E> comparator);
}
