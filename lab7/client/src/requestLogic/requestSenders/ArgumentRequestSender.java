package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import exceptions.ServerNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.ArgumentCommandClientRequest;
import responses.CommandStatusResponse;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.io.Serializable;
import java.net.PortUnreachableException;

public class ArgumentRequestSender<T extends Serializable> {

    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public CommandStatusResponse sendCommand(CommandDescription command, String[] args, T argument, ServerConnection connection) {
        CommandStatusResponse response = null;
        try {
            ArgumentCommandClientRequest<T> rq = new ArgumentCommandClientRequest<>(command, args, argument);
            logger.info("Sending command request...");
            response = (CommandStatusResponse) new RequestSender().sendRequest(rq, connection);
        } catch (PortUnreachableException e) {
            logger.warn("Server is unavailable. Please, wait until server will came back.");
        } catch (ServerNotAvailableException e) {
            logger.error("Your session was expired. Please, wait until server will come back.");
            logger.warn("The application will be terminated.");
            System.exit(0);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations.");
        }
        return response;
    }
}
