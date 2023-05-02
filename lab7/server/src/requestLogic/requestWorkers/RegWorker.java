package requestLogic.requestWorkers;

import clientLogic.ClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;
import requests.RegRequest;
import responseLogic.responseSenders.ResponseSender;
import responses.RegResponse;

import java.io.IOException;

public class RegWorker implements RequestWorker{
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab7.AuthWorker");
    @Override
    public void workWithRequest(ServerRequest request) {
        try {
            RegRequest requestToWork = (RegRequest) request.getUserRequest();
            ClientHandler manager = ClientHandler.getInstance(requestToWork.getName(), requestToWork.getPasswd());
            boolean reg = manager.regUser();
            RegResponse response = new RegResponse(reg);
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        } catch (IOException e) {
            logger.error("something went wrong during i/o ");
        }
    }
}
