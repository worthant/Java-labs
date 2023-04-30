package commandManager.externalRecievers;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.CommandRequestSender;
import responses.CommandStatusResponse;
import serverLogic.ServerConnectionHandler;

public class NonArgumentReceiver implements ExternalBaseReceiver {

    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");

    @Override
    public boolean receiveCommand(CommandDescription command, String[] args) {
        CommandStatusResponse response = new CommandRequestSender().sendCommand(command, args, ServerConnectionHandler.getCurrentConnection());
        if (response != null) {
            logger.info("Status code: " + response.getStatusCode());
            logger.info("Response: \n" + response.getResponse());
            return true;
        }
        return false;
    }
}
