package collectionManagers;

import collection.City.City;

import java.util.TreeSet;

public class IdChecker {
    public static Object checkCityById(String argument) {
        int id = Integer.parseInt(argument);
        TreeSet<City> cities = CollectionManager.getCityCollection();
        Object obj = new Object();
        boolean flag = false;
        for (City city : cities) {
            if (city.getId() == id) {
                obj = city;
                flag = true;
                break;
            }
        }
        if (flag)
            return obj;
        else
            return null;
    }
}
