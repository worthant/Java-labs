package collection.comparators;

import collection.City.City;

import java.util.Comparator;

public class CityComparatorByMetersAboveSeaLevel implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            } else {
                return -1;
            }
        }
        return o1.getMetersAboveSeaLevel().compareTo(o2.getMetersAboveSeaLevel());
    }
}
