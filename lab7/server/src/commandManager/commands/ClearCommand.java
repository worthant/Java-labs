package commandManager.commands;

import clientLogic.ClientHandler;
import collectionStorageManager.PostgreSQLManager;
import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

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

        long userId = ClientHandler.getUserId();

        // Clear the collection in the database for the specified user
        boolean isCleared = postgreSQLManager.clearCitiesForUser(userId);

        if (isCleared) {
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
