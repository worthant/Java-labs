package commandLogic.commandReceiverLogic.handlers;

import commandLogic.commandReceiverLogic.receivers.ExternalArgumentReceiver;
import commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver;

import java.util.ArrayList;

public class ArgumentReceiverHandler<T> extends ReceiverHandler {

    private final ArrayList<ExternalArgumentReceiver<T>> receivers;
    Class<T> argType;

    {
        receivers = new ArrayList<>();
    }

    public ArgumentReceiverHandler(Class<T> argType) {
        this.argType = argType;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void addReceiver(ExternalBaseReceiver receiver) {
        if (receiver instanceof ExternalArgumentReceiver<?>) {
            if (((ExternalArgumentReceiver<?>) receiver).getArguemnt().getClass().getName().equals(argType.getName()))
                receivers.add((ExternalArgumentReceiver<T>) receiver);
        }
    }

    @Override
    public ArrayList<ExternalArgumentReceiver<T>> getReceivers() {
        return receivers;
    }
}
