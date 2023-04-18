package commandManager.commands;

import models.City;
import models.handlers.CollectionHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.TreeSet;

/**
 * Shows every element of the collection in toString() interpretation.
 *
 * @author worthant
 * @since 1.0
 */
public class ShowCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.show");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescr() {
        return "Shows every element of the collection in toString() interpretation.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();

        StringBuilder sb = new StringBuilder();

        collectionHandler.getCollection().forEach(e -> sb.append(e.toString()).append('\n'));
        response = CommandStatusResponse.ofString(sb.toString());

        if (collectionHandler.getCollection().isEmpty()) {
            response = CommandStatusResponse.ofString("There's nothing to show.");
        }

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
