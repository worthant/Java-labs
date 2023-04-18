package commandManager.commands;

import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.TreeSet;

/**
 * Shows information about the collection.
 *
 * @author worthant
 * @since 1.0
 */
public class InfoCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.info");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescr() {
        return "Shows information about the collection.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();

        TreeSet<City> collection = collectionHandler.getCollection();

        String sb = "Now you are operating with collection of type " + collection.getClass().getName() + ", filled with elements of type " + collectionHandler.getFirstOrNew().getClass().getName() + '\n' +
                "Collection size: " + collection.size() + '\n' +
                "Init date: " + collectionHandler.getInitDate();

        response = CommandStatusResponse.ofString(sb);
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
