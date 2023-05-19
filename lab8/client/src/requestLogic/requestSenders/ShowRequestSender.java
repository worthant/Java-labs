package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import exceptions.ServerNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandClientRequest;
import responses.ShowResponse;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.net.PortUnreachableException;

public class ShowRequestSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab8");

    public ShowResponse sendCommand(String name, char[] passwd, CommandDescription command, String[] args, ServerConnection connection) {
        ShowResponse response = null;
        try {
            var rq = new CommandClientRequest(name, passwd, command, args);
            logger.info("Sending command request...");
            response = (ShowResponse) new RequestSender().sendRequest(rq, connection);
        } catch (PortUnreachableException e) {
            logger.warn("Server is unavailable. Please, wait until server will come back.");
        } catch (ServerNotAvailableException e) {
            logger.error("Your session was expired. Please, wait until server will come back.");
            logger.warn("The application will be terminated.");
            System.exit(0);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations", e);
        }
        return response;
    }
}

