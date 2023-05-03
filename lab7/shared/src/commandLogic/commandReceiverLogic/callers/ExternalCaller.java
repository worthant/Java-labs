package commandLogic.commandReceiverLogic.callers;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.ReceiverManager;

import java.io.Serializable;

public abstract class ExternalCaller implements Serializable {
    public abstract void callReceivers(String name, char[] passwd, ReceiverManager manager, CommandDescription description, String[] lineArgs) throws Exception;
}
