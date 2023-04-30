package commandManager.commands;

import models.City;
import models.handlers.CollectionHandler;
import models.handlers.CityIDHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.TreeSet;

/**
 * Adds new element to collection.
 *
 * @author worthant
 * @since 1.0
 */
public class AddCommand implements BaseCommand, ArgumentConsumer<City> {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.add");
    private CommandStatusResponse response;

    private City obj;

    @Override
    public void setObj(City obj) {
        this.obj = obj;
        obj.setId(CityIDHandler.generateId());
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();

        collectionHandler.addElementToCollection(obj);

        response = CommandStatusResponse.ofString("Element added!");
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
