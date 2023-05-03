package requestLogic.requestWorkers;

import requestLogic.requests.ServerRequest;

public interface RequestWorker {
    void workWithRequest(ServerRequest request);
}
