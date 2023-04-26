package commandManager.commands;

import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;


/**
 * Saves collection to file.
 *
 * @author worthant
 * @since 1.0
 */
public class SaveCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.save");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Deprecated (for server-use only).";
    }

    @Override
    public void execute(String[] args) {
        logger.trace("Saving...");
        CityHandler writer = CityHandler.getInstance();
        response = CommandStatusResponse.ofString("[Server] Collection saving executing...\nsize of collection to write: " + writer.getCollection().size());
        logger.info(response.getResponse());
        writer.writeCollection();
        response = CommandStatusResponse.ofString("[Server] Collection saving executed.\nsize of written collection: " + writer.getCollection().size());
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
