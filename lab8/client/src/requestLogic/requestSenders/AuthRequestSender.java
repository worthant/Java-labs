package requestLogic.requestSenders;

import exceptions.ServerNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.AuthRequest;
import responses.AuthResponse;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.net.PortUnreachableException;

public class AuthRequestSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public AuthResponse sendAuthData(String name, char[] passwd, ServerConnection connection) {
        AuthResponse response = null;
        try {
            AuthRequest rq = new AuthRequest(name, passwd);
            logger.info("Sending command request...");
            response = (AuthResponse) new RequestSender().sendRequest(rq, connection);
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



