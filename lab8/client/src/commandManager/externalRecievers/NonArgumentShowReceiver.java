package commandManager.externalRecievers;

import client.DataHolder;
import commandLogic.CommandDescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.ShowRequestSender;
import responses.ShowResponse;
import serverLogic.ServerConnectionHandler;

public class NonArgumentShowReceiver extends NonArgumentReceiver {
    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab8");


    @Override
    public boolean receiveCommand(String name, char[] passwd, CommandDescription command, String[] args) {
        ShowResponse response = new ShowRequestSender().sendCommand(name, passwd, command, args, ServerConnectionHandler.getCurrentConnection());
        return response != null;
    }
}
