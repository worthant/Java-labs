package responseLogic.responseSenders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;
import responses.CommandStatusResponse;
import serverLogic.ServerConnection;

import java.io.IOException;

public class CommandResponseSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public static void sendResponse(CommandStatusResponse response, ServerConnection connection, CallerBack to) {
        try {
            if (response != null) {
                ResponseSender.sendResponse(response, connection, to);
            }
        } catch (IOException e) {
            logger.fatal("Something went wrong during I/O", e);
        }
    }
}
