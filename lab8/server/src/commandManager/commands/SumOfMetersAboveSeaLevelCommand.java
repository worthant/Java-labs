package commandManager.commands;

import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.TreeSet;

/**
 * Computing the sum of meters above sea level of all cities in the collection.
 *
 * @author worthant
 * @since 1.0
 */
public class SumOfMetersAboveSeaLevelCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.sumOfMetersAboveSeaLevel");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "sum_of_meters_above_sea_level";
    }

    @Override
    public String getDescr() {
        return "Prints the sum of the values of the metersAboveSeaLevel field for all elements of the collection.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
        TreeSet<City> cityCollection = collectionHandler.getCollection();

        if (cityCollection.isEmpty()) {
            response = CommandStatusResponse.ofString("There's no elements in the collection");
        } else {
            double sum = cityCollection.stream()
                    .mapToDouble(City::getMetersAboveSeaLevel)
                    .sum();
            response = CommandStatusResponse.ofString("Sum of all meters_above_sea_level fields is: " + sum);
        }

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
