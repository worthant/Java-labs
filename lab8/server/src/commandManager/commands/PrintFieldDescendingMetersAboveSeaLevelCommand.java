package commandManager.commands;

import models.City;
import models.comparators.CityComparatorByMetersAboveSeaLevel;
import models.handlers.CollectionHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Prints all distance fields in ascending sorting.
 *
 * @author worthant
 * @since 1.0
 */
public class PrintFieldDescendingMetersAboveSeaLevelCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.printFDA");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "print_field_ascending_distance";
    }

    @Override
    public String getDescr() {
        return "Prints all distance fields in ascending sorting.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
        TreeSet<City> cityCollection = collectionHandler.getCollection();

        if (cityCollection.isEmpty()) {
            response = CommandStatusResponse.ofString("There's nothing to show...");
        } else {
            String output = cityCollection.stream()
                    .sorted(Comparator.comparing(City::getMetersAboveSeaLevel).reversed())
                    .map(city -> String.valueOf(city.getMetersAboveSeaLevel()))
                    .collect(Collectors.joining("\n"));

            response = CommandStatusResponse.ofString(output);
        }

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
