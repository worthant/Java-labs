package commandManager.commands;

import models.City;
import models.handlers.CityHandler;
import models.handlers.CityIDHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;
import java.util.TreeSet;

/**
 * Removes elements from collection greater than given in argument.
 *
 * @author worthant
 * @since 1.0
 */
public class RemoveGreaterCommand implements BaseCommand, ArgumentConsumer<City> {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.rmGreater");
    private CommandStatusResponse response;
    private City obj;

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescr() {
        return "Removes all elements from the collection that have a population greater than the specified city.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();

        logger.debug("Distance: " + obj.getPopulation());

        var iterator = collectionHandler.getCollection().iterator();
        int count = 0;

        while (iterator.hasNext()) {
            var current = iterator.next();
            logger.debug("Comparing: current -- " + current.getPopulation() + " vs " + obj.getPopulation());
            if (obj.getPopulation() < current.getPopulation()) {
                logger.debug(" -- Greater / Will be removed...");
                count++;
            } else {
                logger.debug(" -- Lower.");
            }
        }

        collectionHandler.getCollection().removeIf(current -> obj.getPopulation() < current.getPopulation());
        response = CommandStatusResponse.ofString("Removed " + count + " elements");
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setObj(City obj) {
        this.obj = obj;
        obj.setId(CityIDHandler.generateId());
        obj.setCreationDate(Date.from(Instant.now()));
    }
}
