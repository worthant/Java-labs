package commandManager.commands;

import clientLogic.ClientHandler;
import collectionStorageManager.PostgreSQLManager;
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
        long ownerId = ClientHandler.getUserId();

        logger.debug("Distance: " + obj.getPopulation());

        PostgreSQLManager dbManager = new PostgreSQLManager();
        int count = 0;

        for (City current : collectionHandler.getCollection()) {
            if (dbManager.isCityOwnedByUser(current.getId(), ownerId) && obj.getPopulation() < current.getPopulation()) {
                if (dbManager.removeCityById(current.getId(), ownerId)) {
                    count++;
                }
            }
        }

        collectionHandler.getCollection().removeIf(current -> dbManager.isCityOwnedByUser(current.getId(), ownerId) && obj.getPopulation() < current.getPopulation());
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
