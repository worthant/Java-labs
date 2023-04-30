package commandManager.externalRecievers;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver;

import java.util.Objects;

public class ExitReceiver implements ExternalBaseReceiver {

    @Override
    public boolean receiveCommand(CommandDescription command, String[] args) {
        if (!Objects.equals(command.getName(), "exit")) return true;

        System.exit(0);
        return false;
    }
}
