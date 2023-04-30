package requestLogic.requestWorkers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;

public class BaseRequestWorker implements RequestWorker {

    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    @Override
    public void workWithRequest(ServerRequest request) {
        logger.info("we've got base request wow");
    }
}
