package commandLogic.commandReceiverLogic.receivers;

public interface ExternalArgumentReceiver<T> extends ExternalBaseReceiver {
    T getArguemnt();
}
