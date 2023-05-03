package commandManager.commands;

import clientLogic.ClientHandler;
import collectionStorageManager.PostgreSQLManager;
import models.City;
import models.handlers.CityIDHandler;
import models.handlers.CollectionHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Updates element by its ID.
 *
 * @author worthant
 * @since 1.0
 */
public class UpdateCommand implements BaseCommand, ArgumentConsumer<City> {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.update");
    private CommandStatusResponse response;
    private City obj;

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescr() {
        return "Updates the value of the collection element whose id is equal to the given.";
    }

    @Override
    public String getArgs() {
        return "id {element}";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
        long finalId = Long.parseLong(args[1]);

        PostgreSQLManager dbManager = new PostgreSQLManager();

        if (!dbManager.isCityOwnedByUser(finalId)) {
            response = new CommandStatusResponse("Element with that id doesn't exist or you don't have permission to modify it.", 2);
            logger.warn(response.getResponse());
            return;
        }

        obj.setId(finalId); // Set ID before updating city in the database
        boolean updated = dbManager.updateCity(obj); // Updating city in the database

        if (updated) {
            // Update the city in the collection
            collectionHandler.getCollection().removeIf(city -> Objects.equals(city.getId(), finalId));
            collectionHandler.addElementToCollection(obj);

            response = CommandStatusResponse.ofString("Object updated!\n finalId: " + finalId);
        } else {
            response = new CommandStatusResponse("Failed to update the object.", 2);
        }
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
