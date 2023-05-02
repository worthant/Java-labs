package responseLogic.responseSenders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;
import responses.CommandDescriptionsResponse;
import serverLogic.ServerConnection;

import java.io.IOException;

public class CommandConfigureResponseSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public static void sendResponse(CommandDescriptionsResponse response, ServerConnection connection, CallerBack to) {
        if (response != null) {
            ResponseSender.sendResponse(response, connection, to);
        }
    }
}
