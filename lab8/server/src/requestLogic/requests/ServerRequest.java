package requestLogic.requests;

import requestLogic.CallerBack;
import requests.BaseRequest;
import serverLogic.ServerConnection;

public class ServerRequest {
    private final BaseRequest request;
    private final CallerBack from;
    private final ServerConnection connection;

    public ServerRequest(BaseRequest request, CallerBack from, ServerConnection connection) {
        this.request = request;
        this.from = from;
        this.connection = connection;
    }

    public BaseRequest getUserRequest() {
        return request;
    }

    public ServerConnection getConnection() {
        return connection;
    }

    public CallerBack getFrom() {
        return from;
    }
}
