package commandManager.externalRecievers;

import client.DataHolder;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    public boolean receiveCommand(String name, char[] passwd, CommandDescription command, String[] args) throws BuildObjectException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        city = modeManager.buildObject();

        Future<CommandStatusResponse> futureResponse = service.submit(() -> new ArgumentRequestSender<City>().sendCommand(name, passwd, command, args, city, ServerConnectionHandler.getCurrentConnection()));

        try {
            CommandStatusResponse response = futureResponse.get();

            if (response != null) {
                logger.info("Status code: " + response.getStatusCode());
                logger.info("Response: \n" + response.getResponse());
                DataHolder.getInstance().setBaseResponse(response);
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public City getArguemnt() {
        return city;
    }
}
