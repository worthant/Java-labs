package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import exceptions.ServerNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandDescriptionsRequest;
import responses.CommandDescriptionsResponse;
import serverLogic.ServerConnectionHandler;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.util.ArrayList;

public class CommandDescriptionsRequestSender {

    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public ArrayList<CommandDescription> sendRequestAndGetCommands() {
        ArrayList<CommandDescription> result = null;

        CommandDescriptionsRequest request = new CommandDescriptionsRequest();

        try {
            CommandDescriptionsResponse response = (CommandDescriptionsResponse) new RequestSender().sendRequest(request, ServerConnectionHandler.getCurrentConnection());
            result = response.getCommands();
        } catch (PortUnreachableException e) {
            logger.fatal("Server is unavailable. Please, wait until server will came back.");
            logger.fatal("We can't get available commands because the server is unavailable.");
        } catch (ServerNotAvailableException e) {
            logger.fatal("Server is busy. Please, wait until server will came back.");
            logger.fatal("We can't get available commands because the server is busy.");
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations.");
        }

        return result;
    }
}
