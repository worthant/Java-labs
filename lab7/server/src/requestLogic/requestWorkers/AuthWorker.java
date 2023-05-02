package requestLogic.requestWorkers;

import clientLogic.ClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;
import requests.AuthRequest;
import responseLogic.responseSenders.ResponseSender;
import responses.AuthResponse;

import java.io.IOException;

public class AuthWorker implements RequestWorker{
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab7.AuthWorker");
    @Override
    public void workWithRequest(ServerRequest request) {
        try {
            AuthRequest requestToWork = (AuthRequest) request.getUserRequest();
            ClientHandler manager = ClientHandler.getInstance(requestToWork.getName(), requestToWork.getPasswd());
            boolean auth = manager.authUser();
            AuthResponse response = new AuthResponse(auth);
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        } catch (IOException e) {
            logger.error("something went wrong during i/o ");
        }
    }
}
