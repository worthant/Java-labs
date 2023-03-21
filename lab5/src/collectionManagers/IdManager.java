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

    /**
     * A queue to keep track of IDs that have been generated
     */
    private static Queue<Long> queue;

    /**
     * Initializes the queue for ID storage
     */
    static {
        queue = new LinkedList<>();
    }

    /**
     * Checks if a City object with the given ID exists in the collection, and if so, returns it
     *
     * @param id the ID to check
     * @return the City object with the specified ID if it exists, otherwise null
     */
    public static City checkCityById(Long id) {
        CollectionManager<TreeSet<City>, City> collectionHandler = CityManager.getInstance();
        for (City city : collectionHandler.getCollection()) {
            if (city.getId() == id)
                return city;
        }
        return null;
    }

    /**
     * Generates a unique ID that has not been previously generated and stores it in the queue
     *
     * @return the unique ID that was generated
     */
    public static Long generateId() {
        Long id;
        do {
            id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        } while (queue.contains(id));
        queue.add(id);
        return id;
    }

    /**
     * Checks if a given string is not numeric
     *
     * @param str the string to check
     * @return true if the string is not numeric, false otherwise
     */
    public static boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    /**
     * Validates user input for ID fields and returns the parsed ID if it is valid
     *
     * @param input the user input to validate
     * @return the parsed ID if it is valid, otherwise null
     */
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
