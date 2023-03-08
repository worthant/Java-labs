package collectionManagers;

import collection.City.City;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for managing object's id's
 */
public class IdManager {
    public static long id;
    private static Queue<Long> queue;
    /**
     * Checking if the object with the given id exists and if so, return it
     * @param argument the id to check
     * @return the object with the specified id if it exists, otherwise null
     */

    static {
        queue = new LinkedList<>();
    }
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

    public static Long generateId() {
        Long id;
        do {
            id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        } while (queue.contains(id));
        queue.add(id);
        return id;
    }

    /**
     * Method for getting the current id value
     * @return the current id value
     */
    public static long getId() {
        return id;
    }
}
