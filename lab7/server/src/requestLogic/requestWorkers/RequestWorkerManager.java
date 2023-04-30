package requestLogic.requestWorkers;

import exceptions.UnsupportedRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;
import requests.ArgumentCommandClientRequest;
import requests.BaseRequest;
import requests.CommandClientRequest;
import requests.CommandDescriptionsRequest;

import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestWorkerManager {

    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    private final LinkedHashMap<Class<?>, RequestWorker> workers = new LinkedHashMap<>();

    public RequestWorkerManager() {
        workers.put(BaseRequest.class, new BaseRequestWorker());
        workers.put(CommandClientRequest.class, new CommandClientRequestWorker());
        workers.put(ArgumentCommandClientRequest.class, new ArgumentCommandClientRequestWorker<>());
        workers.put(CommandDescriptionsRequest.class, new CommandConfigureRequestWorker());
    }

    public void workWithRequest(ServerRequest request) {
        try {
            Optional.ofNullable(workers.get(request.getUserRequest().getClass())).orElseThrow(() -> new UnsupportedRequestException("Указанный запрос не может быть обработан")).workWithRequest(request);
        } catch (UnsupportedRequestException ex) {
            logger.error("Got an invalid request.");
        }
    }
}
