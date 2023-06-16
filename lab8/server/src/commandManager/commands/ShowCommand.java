package commandManager.commands;

import collectionStorageManager.PostgreSQLManager;
import models.City;
import models.handlers.CollectionHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;
import responses.ShowResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Shows every element of the collection in toString() interpretation.
 *
 * @author worthant
 * @since 1.0
 */
public class ShowCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.show");
    private ShowResponse response;

    @Deprecated
    private CommandStatusResponse oldResponse;

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescr() {
        return "Shows every element of the collection in toString() interpretation.";
    }

    /**
     * New execute method, that just passes City collection from server to client
     * @param args full array of entered line.
     */
    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();

        // Set the response as ShowResponse with the cityTreeSet
        response = ShowResponse.of("Command executed successfully.", collectionHandler.getCollection());
    }



    /**
     * old method, that executes old cool pages logic
     * I am proud of it, hence I don't want to delete it ^-^
     */
    @Deprecated
    public void executeOld(String[] args) {
        PostgreSQLManager manager = new PostgreSQLManager();

        logger.debug("Received args: " + Arrays.toString(args));
        int itemsPerPage = 10; // You can change this value to your desired number of items per page
        int pageNumber = 0;

        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
        List<City> cityList = new ArrayList<>(manager.getCollectionFromDatabase());
        collectionHandler.addMissingCitiesToCollection(cityList);
        int totalPages = (int) Math.ceil((double) cityList.size() / itemsPerPage);

        String output;
        if (args != null && args.length > 1) {
            try {
                pageNumber = Integer.parseInt(args[1]) - 1;
            } catch (NumberFormatException e) {
                oldResponse = CommandStatusResponse.ofString("Invalid page number.");
                logger.warn(oldResponse.getResponse());
                return;
            }

            if (pageNumber < 0 || pageNumber >= totalPages) {
                oldResponse = CommandStatusResponse.ofString("Page number out of range.");
                logger.warn(oldResponse.getResponse());
                return;
            }

            output = IntStream.range(itemsPerPage * pageNumber, Math.min(itemsPerPage * (pageNumber + 1), cityList.size()))
                    .mapToObj(i -> cityList.get(i).toString())
                    .collect(Collectors.joining("\n"));

            oldResponse = CommandStatusResponse.ofString("Page " + (pageNumber + 1) + " of " + totalPages + ":\n" + output);
            logger.info(oldResponse.getResponse());

        } else {
            output = "Total pages: " + totalPages;
            oldResponse = CommandStatusResponse.ofString(output);
        }
    }



    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
