package commandManager.commands;

import collectionStorageManager.PostgreSQLManager;
import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;
import responses.GetOwnershipResponse;
import responses.ShowResponse;

import java.util.TreeSet;

public class GetOwnershipCommand implements BaseCommand{
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.show");
    private GetOwnershipResponse response;

    @Override
    public String getName() {
        return "get_ownership";
    }
    @Override
    public String getDescr() {
        return "Returns pairs of (city_id, client_name) in Map<Integer, String>";
    }

    @Override
    public void execute(String[] args) {
        PostgreSQLManager manager = new PostgreSQLManager();

        // Set the response as ShowResponse with the cityTreeSet
        response = GetOwnershipResponse.of("Command executed successfully.", manager.getOwnerShipMap());
    }


    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
