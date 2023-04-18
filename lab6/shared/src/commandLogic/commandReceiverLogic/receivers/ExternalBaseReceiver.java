package commandLogic.commandReceiverLogic.receivers;

import commandLogic.CommandDescription;

public interface ExternalBaseReceiver {
    boolean receiveCommand(CommandDescription command, String[] args) throws Exception;
}
