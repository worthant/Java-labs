package models.comparators;

import models.City;

import java.io.Serializable;
import java.util.Comparator;

/**
 * This class provides a comparator for the City class that compares two cities
 * based on their natural order using the compareTo method of the City class.
 */
public class CityComparator implements Comparator<City>, Serializable {

    /**
     * Compares two City objects based on their natural order using the compareTo method.
     * @param o1 the first City object to be compared
     * @param o2 the second City object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(City o1, City o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            } else {
                return -1;
            }
        }
        return o1.compareTo(o2);
    }
}
