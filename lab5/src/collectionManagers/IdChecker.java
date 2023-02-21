package collectionManagers;

import collection.City.City;

import java.util.TreeSet;

public class IdChecker {
    public static long id;
    public static Object checkCityById(String argument) {
        long id = Long.parseLong(argument);
        TreeSet<City> cities = CollectionManager.getCityCollection();
        Object obj = new Object();
        boolean flag = false;
        for (City city : cities) {
            if (city.getId() == id) {
                obj = city;
                id = city.getId();
                flag = true;
                break;
            }
        }
        if (flag)
            return obj;
        else
            return null;
    }

    public static long getId() {
        return id;
    }
}
