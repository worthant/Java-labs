package exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;

public class NotAvailableException extends Exception {

    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");
    private final CallerBack deniedClient;

    public NotAvailableException(CallerBack deniedClient) {
        this.deniedClient = deniedClient;
        logger.info("Denied connection: " + deniedClient);
    }

    public CallerBack getDeniedClient() {
        return deniedClient;
    }
}
