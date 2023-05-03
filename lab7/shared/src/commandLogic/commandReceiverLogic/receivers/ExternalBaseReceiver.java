package commandLogic.commandReceiverLogic.receivers;

import commandLogic.CommandDescription;

public interface ExternalBaseReceiver {
    boolean receiveCommand(String name, char[] passwd, CommandDescription command, String[] args) throws Exception;
}
