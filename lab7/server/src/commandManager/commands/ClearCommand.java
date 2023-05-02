package commandManager.commands;

import clientLogic.ClientHandler;
import collectionStorageManager.PostgreSQLManager;
import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.List;
import java.util.TreeSet;

/**
 * Clears collection
 *
 * @author worthant
 * @since 1.0
 */
public class ClearCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.clear");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "Clears collection";
    }

    @Override
    public void execute(String[] args) {
        CityHandler cityHandler = CityHandler.getInstance();
        PostgreSQLManager postgreSQLManager = new PostgreSQLManager();

        // Clear the collection in the database for the specified user and get the list of deleted city IDs
        List<Long> deletedCityIds = postgreSQLManager.clearCitiesForUser();

        if (!deletedCityIds.isEmpty()) {
            // Remove cities with the deleted city IDs from the in-memory collection
            cityHandler.getCollection().removeIf(city -> deletedCityIds.contains(city.getId()));
            response = CommandStatusResponse.ofString("Cleared!");
        } else {
            response = CommandStatusResponse.ofString("Failed to clear the collection.");
        }

        logger.info(response.getResponse());
    }


    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
