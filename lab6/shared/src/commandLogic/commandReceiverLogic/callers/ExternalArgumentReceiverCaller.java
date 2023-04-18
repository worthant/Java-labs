package commandLogic.commandReceiverLogic.callers;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.ReceiverManager;
import commandLogic.commandReceiverLogic.enums.ReceiverType;
import commandLogic.commandReceiverLogic.receivers.ExternalArgumentReceiver;

import java.util.ArrayList;

public class ExternalArgumentReceiverCaller<T> extends ExternalCaller {
    @Override
    public void callReceivers(ReceiverManager manager, CommandDescription description, String[] lineArgs) throws Exception {
        for (ExternalArgumentReceiver<T> receiver : (ArrayList<ExternalArgumentReceiver<T>>) manager.getReceivers(ReceiverType.ArgumentRoute)) {
            receiver.receiveCommand(description, lineArgs);
        }
    }
}
