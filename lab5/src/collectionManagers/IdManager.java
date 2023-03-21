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

    static {
        queue = new LinkedList<>();
    }

    /**
     * Checking if the object with the given id exists and if so, return it
     * @param id the id to check
     * @return the object with the specified id if it exists, otherwise null
     */
    public static City checkCityById(Long id) {
        CollectionManager<TreeSet<City>, City> collectionHandler = CityManager.getInstance();
        for (City city : collectionHandler.getCollection()) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    /**
     * Generating unique id
     * @return unique generated id
     */
    public static Long generateId() {
        Long id;
        do {
            id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        } while (queue.contains(id));
        queue.add(id);
        return id;
    }

    public static boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static Long validateUserInput(String input) {
        if (IdManager.isNotNumeric(input)) {
            System.out.println("Provided argument id: \"" + input + "\" is not a number! Try again.");
            return null;
        } else if (input.contains(".")) {
            System.out.println("ID field cannot accept decimal values. Try again.");
            return null;
        }

        Long id = null;
        try {
            id = Long.valueOf(input);
        } catch (NumberFormatException e) {
            System.out.println("Provided argument: \"" + input + "\" is too large for ID field. Try again.");
        }
        return id;
    }
}
