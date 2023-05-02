package commandManager.commands;

import models.City;
import models.handlers.CollectionHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.TreeSet;
import java.util.stream.Collectors;

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

        String output = collectionHandler.getCollection().stream()
                .map(City::toString)
                .collect(Collectors.joining("\n"));

        response = output.isEmpty()
                ? CommandStatusResponse.ofString("There's nothing to show.")
                : CommandStatusResponse.ofString(output);

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
