package commandManager.commands;

import clientLogic.ClientHandler;
import collectionStorageManager.PostgreSQLManager;
import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Removes element from collection by id.
 *
 * @author worthant
 * @since 1.0
 */
public class RemoveByIdCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.rmByID");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by id.";
    }

    @Override
    public String getArgs() {
        return "id";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
        long ownerId = ClientHandler.getUserId();
        long cityId = Long.parseLong(args[1]);

        PostgreSQLManager dbManager = new PostgreSQLManager();

        if (!dbManager.isCityOwnedByUser(cityId, ownerId)) {
            boolean removed = dbManager.removeCityById(cityId, ownerId);
            if (removed) {
                collectionHandler.getCollection().removeIf(city -> Objects.equals(city.getId(), cityId));
                response = CommandStatusResponse.ofString("Element removed.");
            } else {
                response = CommandStatusResponse.ofString("Failed to remove the element.");
            }
        } else {
            response = CommandStatusResponse.ofString("Element with that id doesn't exist or you don't have permission to remove it.");
        }

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
