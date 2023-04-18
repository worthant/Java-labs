package models.handlers;

import models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class CityIDHandler {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

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
     * @deprecated wrote better checking in removeByIdCommand
     */
    @Deprecated
    public static City checkCityById(Long id) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
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
}
