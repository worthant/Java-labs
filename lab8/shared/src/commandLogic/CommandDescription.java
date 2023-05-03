package commandLogic;

import commandLogic.commandReceiverLogic.callers.ExternalCaller;

import java.io.Serializable;

public class CommandDescription implements Serializable {
    private final String name;
    private final ExternalCaller caller;

    public CommandDescription(String name, ExternalCaller caller) {
        this.name = name;
        this.caller = caller;
    }

    public String getName() {
        return name;
    }

    public ExternalCaller getReceiver() {
        return caller;
    }
}
