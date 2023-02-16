package collection;

import java.util.Comparator;

public class CityComparator implements Comparator<City> {

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
