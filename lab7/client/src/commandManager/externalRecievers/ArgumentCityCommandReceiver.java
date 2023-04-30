package commandManager.externalRecievers;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.receivers.ExternalArgumentReceiver;
import exceptions.BuildObjectException;
import models.City;
import models.handlers.ModeManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.ArgumentRequestSender;
import responses.CommandStatusResponse;
import serverLogic.ServerConnectionHandler;

public class ArgumentCityCommandReceiver implements ExternalArgumentReceiver<City> {

    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");
    ModeManager<City> modeManager;
    City city;

    {
        city = new City();
    }

    public ArgumentCityCommandReceiver(ModeManager<City> modeManager) {
        this.modeManager = modeManager;
    }

    @Override
    public boolean receiveCommand(CommandDescription command, String[] args) throws BuildObjectException {
        city = modeManager.buildObject();
        CommandStatusResponse response = new ArgumentRequestSender<City>().sendCommand(command, args, city, ServerConnectionHandler.getCurrentConnection());
        if (response != null) {
            logger.info("Status code: " + response.getStatusCode());
            logger.info("Response: \n" + response.getResponse());
            return true;
        }
        return false;
    }

    @Override
    public City getArguemnt() {
        return city;
    }
}
