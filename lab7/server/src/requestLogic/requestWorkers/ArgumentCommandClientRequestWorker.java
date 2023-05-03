package requestLogic.requestWorkers;

import clientLogic.ClientHandler;
import commandManager.CommandManager;
import commandManager.commands.ArgumentConsumer;
import exceptions.UserNotAuthenticatedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;
import requests.ArgumentCommandClientRequest;

public class ArgumentCommandClientRequestWorker<T, Y> implements RequestWorker {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    @Override
    public void workWithRequest(ServerRequest request) {
        try {
            ArgumentCommandClientRequest<T> requestToWork = (ArgumentCommandClientRequest<T>) request.getUserRequest();

            ClientHandler clientManager = new ClientHandler(requestToWork.getName(), requestToWork.getPasswd());
            clientManager.authUserCommand();

            CommandManager manager = new CommandManager();
            ArgumentConsumer<T> argCommand = (ArgumentConsumer<T>) manager.fromDescription(requestToWork.getCommandDescription());
            argCommand.setObj(requestToWork.getArgument());
            manager.executeCommand(requestToWork, request.getFrom(), request.getConnection());
        } catch (UserNotAuthenticatedException e) {
            logger.error("Something went wrong during authentication: " + e.getMessage());
        }
    }
}
