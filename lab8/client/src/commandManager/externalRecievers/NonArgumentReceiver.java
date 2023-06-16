package commandManager.externalRecievers;

import client.DataHolder;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.CommandRequestSender;
import responses.CommandStatusResponse;
import serverLogic.ServerConnectionHandler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class NonArgumentReceiver implements ExternalBaseReceiver {

    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");

    @Override
    public boolean receiveCommand(String name, char[] passwd, CommandDescription command, String[] args) {
        Future<CommandStatusResponse> futureResponse = new CommandRequestSender().sendCommand(name, passwd, command, args, ServerConnectionHandler.getCurrentConnection());
        try {
            CommandStatusResponse response = futureResponse.get();
            if (response != null) {
                DataHolder.getInstance().setBaseResponse(response);
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

}
