package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import exceptions.ServerNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandClientRequest;
import responses.GetOwnershipResponse;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.net.PortUnreachableException;

public class GetOwnershipRequestSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab8");

    public GetOwnershipResponse sendCommand(String name, char[] passwd, CommandDescription command, String[] args, ServerConnection connection) {
        GetOwnershipResponse response = null;
        try {
            var rq = new CommandClientRequest(name, passwd, command, args);
            logger.info("Sending command request...");
            response = (GetOwnershipResponse) new RequestSender().sendRequest(rq, connection);
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
