package collectionManagers;

import collection.City.City;

import java.util.TreeSet;

/**
 * Class for managing object's id's
 */
public class CityIdChecker {
    public static long id;

    /**
     * Checking if the object with the given id exists and if so, return it
     * @param argument the id to check
     * @return the object with the specified id if it exists, otherwise null
     */
    public static Object checkCityById(String argument) {
        long id = Long.parseLong(argument);
        TreeSet<City> cities = CityManager.getCityCollection();
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

    /**
     * Method for getting the current id value
     * @return the current id value
     */
    public static long getId() {
        return id;
    }
}
