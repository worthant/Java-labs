package collection;

import collection.City;

import java.util.TreeSet;

public class CollectionManager {
    private static TreeSet<City> cityTreeSet;
    public static void setCollection(TreeSet<City> cityTreeSet) {
        CollectionManager.cityTreeSet = cityTreeSet;
    }
}
