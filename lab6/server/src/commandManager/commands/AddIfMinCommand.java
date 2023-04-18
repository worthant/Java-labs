package commandManager.commands;

import models.City;
import models.handlers.CollectionHandler;
import models.handlers.CityIDHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;
import java.util.TreeSet;

/**
 * Adds element if it's value lower than min value.
 *
 * @author worthant
 * @since 1.0
 */
public class AddIfMinCommand implements BaseCommand, ArgumentConsumer<City> {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.addIfMin");
    private CommandStatusResponse response;
    private City obj;

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescr() {
        return "Adds element if it's value lower than min value.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();

        if (obj.getPopulation() < collectionHandler.getCollection().first().getPopulation()) {
            collectionHandler.addElementToCollection(obj);
            response = CommandStatusResponse.ofString("Element added!");
        } else {
            response = new CommandStatusResponse("Element not added: it's not lower than min value.", 3);
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
