package requestLogic.requestWorkers;

import clientLogic.ClientHandler;
import commandManager.CommandManager;
import exceptions.UserNotAuthenticatedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;
import requests.CommandClientRequest;

public class CommandClientRequestWorker implements RequestWorker {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    @Override
    public void workWithRequest(ServerRequest request) {
        try {
            CommandClientRequest requestToWork = (CommandClientRequest) request.getUserRequest();

            ClientHandler clientManager = new ClientHandler(requestToWork.getName(), requestToWork.getPasswd());
            clientManager.authUserCommand();

            CommandManager manager = new CommandManager();
            manager.executeCommand(requestToWork, request.getFrom(), request.getConnection());
        } catch (UserNotAuthenticatedException e) {
            logger.error("Something went wrong during authentication: " + e.getMessage());
        }
    }
}
